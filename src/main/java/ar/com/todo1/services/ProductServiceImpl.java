package ar.com.todo1.services;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.joda.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.todo1.auth.entities.CustomUser;
import ar.com.todo1.entities.Product;
import ar.com.todo1.enums.Errors;
import ar.com.todo1.enums.KardexType;
import ar.com.todo1.exceptions.StoreException;
import ar.com.todo1.interfaces.IComplementABM;
import ar.com.todo1.interfaces.IKardexService;
import ar.com.todo1.interfaces.IProductService;
import ar.com.todo1.models.ProductModel;
import ar.com.todo1.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements IProductService {
	private final ProductRepository iProductRepository;
	private final IKardexService iKardexService;

	public List<Product> listProduct() {
		List<Product> listAvailables = iProductRepository.findAll();
		return listAvailables.stream().filter(product -> product.getUserUnregistry() == null)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return iProductRepository.findById(id);
	}

	@Override
	public Product newProduct(Product product, CustomUser user) throws StoreException {
		try {
			product = saveProduct(product, user);
			ProductModel newProduct = ProductModel.builder().idProduct(product.getId()).count(product.getStock())
					.build();
			iKardexService.insertKardex(newProduct, KardexType.INICIAL);
		} catch (StoreException exception) {
			StoreException storeException = new StoreException(exception, Errors.PRODUCT_NEW.getCode(),
					Errors.PRODUCT_NEW.getDescription());
			log.severe(String.join(" ", storeException.getCode(), storeException.getDescription(),
					storeException.getLocalizedMessage()));
			throw storeException;
		}
		return product;
	}

	@Override
	public Product buyProduct(ProductModel productModel, CustomUser user) throws StoreException {
		try {
			Product product = iProductRepository.findById(productModel.getIdProduct()).get();
			product.setStock(product.getStock().add(productModel.getCount()));
			iKardexService.insertKardex(productModel, KardexType.COMPRA);
			return saveProduct(product, user);
		} catch (Exception exception) {
			StoreException storeException = new StoreException(exception, Errors.PRODUCT_BUY.getCode(),
					Errors.PRODUCT_BUY.getDescription());
			log.severe(String.join(" ", storeException.getCode(), storeException.getDescription(),
					storeException.getLocalizedMessage()));
			throw storeException;
		}
	}

	@Override
	public Product saleProduct(ProductModel productModel, CustomUser user) throws StoreException {
		try {
			if (hasStock(productModel) && hasAvaibleStock(productModel)) {
				Product product = iProductRepository.findById(productModel.getIdProduct()).get();
				product.setStock(product.getStock().subtract(productModel.getCount()));
				iKardexService.insertKardex(productModel, KardexType.VENTA);
				return saveProduct(product, user);
			} else {
				return null;
			}
		} catch (Exception exception) {
			StoreException storeException = new StoreException(exception, Errors.PRODUCT_SALE.getCode(),
					Errors.PRODUCT_SALE.getDescription());
			log.severe(String.join(" ", storeException.getCode(), storeException.getDescription(),
					storeException.getLocalizedMessage()));
			throw storeException;
		}
	}

	@Override
	public Boolean hasStock(ProductModel productModel) throws StoreException {
		Boolean response = Boolean.TRUE;
		Product product = iProductRepository.findById(productModel.getIdProduct()).get();
		if (product.getStock().compareTo(BigInteger.ZERO) <= 0) {
			response = false;
			StoreException storeException = new StoreException(Errors.PRODUCT_OUT_OF_STOCK.getCode(),
					Errors.PRODUCT_OUT_OF_STOCK.getDescription());
			log.severe(String.join(" ", storeException.getCode(), storeException.getDescription()));
			throw storeException;
		}
		return response;
	}

	@Override
	public Boolean hasAvaibleStock(ProductModel productModel) throws StoreException {
		Boolean response = Boolean.TRUE;
		Product product = iProductRepository.findById(productModel.getIdProduct()).get();
		if (product.getStock().compareTo(productModel.getCount()) < 0) {
			response = false;
			StoreException storeException = new StoreException(Errors.PRODUCT_NO_STOCK_AVAILABLE.getCode(),
					Errors.PRODUCT_NO_STOCK_AVAILABLE.getDescription());
			log.severe(String.join(" ", storeException.getCode(), storeException.getDescription()));
			throw storeException;
		}
		return response;
	}

	@Override
	public Product saveProduct(Product product, CustomUser user) throws StoreException {
		try {
			IComplementABM complementABM = (object, id) -> {
				Product productABM = (Product) object;
				if (productABM.getId() == null) {
					return productABM.withUserRegistry(id).withDateRegistry(Instant.now().toDate());
				} else {
					return productABM.withUserModify(id).withDateModify(Instant.now().toDate());
				}
			};
			product = (Product) complementABM.execute(product, user.getId());
			return iProductRepository.save(product);
		}catch (Exception exception) {
			StoreException storeException = new StoreException(exception, Errors.PRODUCT_SAVE.getCode(),
					Errors.PRODUCT_SAVE.getDescription());
			log.severe(String.join(" ", storeException.getCode(), storeException.getDescription(),
					storeException.getLocalizedMessage()));
			throw storeException;
		}
	}

	@Override
	public Product deleteProduct(Product product, CustomUser user) throws StoreException {
		try {
		product.withUserUnregistry(user.getId()).withDateUnregistry(Instant.now().toDate());
		return iProductRepository.save(product);
		}catch (Exception exception) {
			StoreException storeException = new StoreException(exception, Errors.PRODUCT_DELETE.getCode(),
					Errors.PRODUCT_DELETE.getDescription());
			log.severe(String.join(" ", storeException.getCode(), storeException.getDescription(),
					storeException.getLocalizedMessage()));
			throw storeException;
		}
	}

}
