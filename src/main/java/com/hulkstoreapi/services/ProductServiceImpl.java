package com.hulkstoreapi.services;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hulkstoreapi.entities.Kardex;
import com.hulkstoreapi.entities.Product;
import com.hulkstoreapi.entities.models.ProductModel;
import com.hulkstoreapi.exceptions.ProductException;
import com.hulkstoreapi.interfaces.IProductService;
import com.hulkstoreapi.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements IProductService {
	private final ProductRepository iProductRepository;
	private final KardexServiceImpl kardexServiceImpl;

	public List<Product> listProduct() {
		return iProductRepository.findAll();
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return iProductRepository.findById(id);
	}

	@Override
	public Product newProduct(Product product) throws ProductException {
		try {
			Kardex kardex = new Kardex();
			product = iProductRepository.save(product);
			kardex.setIdProduct(product.getId());
			kardex.setDescription("STOCK INICIAL");
			kardex.setDate(new Date());
			kardex.setCount(product.getStock());
			kardexServiceImpl.newKardexProduct(kardex);
		} catch (ProductException productException) {
			log.severe(productException.getMessage());
			throw new ProductException("Error guardando producto");
		}
		return product;
	}

	@Override
	public Product buyProduct(ProductModel productModel) throws ProductException {
		try {
			Product product = iProductRepository.findById(productModel.getIdProduct()).get();
			Kardex kardex = new Kardex();
			product.setStock(product.getStock().add(productModel.getCount()));
			iProductRepository.save(product);
			kardex.setIdProduct(product.getId());
			kardex.setDescription(productModel.getReason());
			kardex.setDate(productModel.getDate());
			kardex.setCount(productModel.getCount());
			kardexServiceImpl.newKardexProduct(kardex);
			return product;
		} catch (Exception productException) {
			log.severe(productException.getMessage());
			throw new ProductException("Error en ingreso de compra");
		}
	}

	@Override
	public Product saleProduct(ProductModel productModel) throws ProductException {
		try {
			if (hasStock(productModel) && hasAvaibleStock(productModel)) {
				Product product = iProductRepository.findById(productModel.getIdProduct()).get();
				Kardex kardex = new Kardex();
				product.setStock(product.getStock().subtract(productModel.getCount()));
				iProductRepository.save(product);
				kardex.setIdProduct(product.getId());
				kardex.setDescription(productModel.getReason());
				kardex.setDate(productModel.getDate());
				kardex.setCount(BigInteger.ZERO.subtract(productModel.getCount()));
				kardexServiceImpl.newKardexProduct(kardex);
				return product;
			} else {
				return null;
			}
		} catch (Exception productException) {
			log.severe(productException.getMessage());
			throw new ProductException("Error en ingreso de compra");
		}
	}

	@Override
	public Boolean hasStock(ProductModel productModel) throws ProductException {
		Boolean response = Boolean.TRUE;
		Product product = iProductRepository.findById(productModel.getIdProduct()).get();
		if (product.getStock().compareTo(BigInteger.ZERO) <= 0) {
			response = false;
			throw new ProductException(
					String.join(" ", "No hay stock para el producto:", product.getDescription().toString()));
		}
		return response;
	}

	@Override
	public Boolean hasAvaibleStock(ProductModel productModel) throws ProductException {
		Boolean response = Boolean.TRUE;
		Product product = iProductRepository.findById(productModel.getIdProduct()).get();
		if (product.getStock().compareTo(productModel.getCount()) < 0) {
			response = false;
			throw new ProductException(String.join(" ", "No hay stock disponible para la venta del producto:",
					product.getDescription().toString()));
		}
		return response;
	}

}
