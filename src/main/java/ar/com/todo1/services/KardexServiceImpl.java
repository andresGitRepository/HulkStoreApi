package ar.com.todo1.services;

import java.util.List;

import org.joda.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.todo1.entities.Kardex;
import ar.com.todo1.enums.Errors;
import ar.com.todo1.enums.KardexType;
import ar.com.todo1.exceptions.StoreException;
import ar.com.todo1.interfaces.IKardexService;
import ar.com.todo1.models.ProductModel;
import ar.com.todo1.repositories.KardexRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

/*** @author Andres Gonzalez ***/

@Log
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KardexServiceImpl implements IKardexService {

	private final KardexRepository kardexRepository;

	@Override
	public Kardex getKardex(Integer id) {
		return kardexRepository.findById(id).get();
	}

	@Override
	public List<Kardex> searchKardexs() throws StoreException {
		try {
			return kardexRepository.findAll();
		} catch (Exception exception) {
			StoreException storeException = new StoreException(exception, Errors.KARDEX_SEARCH.getCode(),
					Errors.KARDEX_SEARCH.getDescription());
			log.severe(String.join(" ", storeException.getCode(), storeException.getDescription(),
					storeException.getLocalizedMessage()));
			throw storeException;
		}
	}

	@Override
	public List<Kardex> searchKardexProduct(Integer idProduct) throws StoreException {
		try {
			return kardexRepository.findByIdProduct(idProduct);
		} catch (Exception exception) {
			StoreException storeException = new StoreException(exception, Errors.KARDEX_SEARCH.getCode(),
					Errors.KARDEX_SEARCH.getDescription());
			log.severe(String.join(" ", storeException.getCode(), storeException.getDescription(),
					storeException.getLocalizedMessage()));
			throw storeException;
		}
	}

	@Override
	public Kardex insertKardex(ProductModel productModel, KardexType type) throws StoreException {
		try {
			Kardex kardex = Kardex.builder().idProduct(productModel.getIdProduct()).description(type.getDescription())
					.date(Instant.now().toDate()).build();
			if (type.getCode().equals(KardexType.VENTA.getCode()))
				kardex.setCount(productModel.getCount().negate());
			else
				kardex.setCount(productModel.getCount());
			return kardexRepository.save(kardex);
		} catch (Exception exception) {
			log.severe(exception.getLocalizedMessage());
			throw new StoreException(exception, Errors.KARDEX_SAVE.getCode(), Errors.KARDEX_SAVE.getDescription());
		}
	}

}
