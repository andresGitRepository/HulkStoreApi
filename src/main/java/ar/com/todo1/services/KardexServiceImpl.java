package ar.com.todo1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.todo1.entities.Kardex;
import ar.com.todo1.exceptions.ProductException;
import ar.com.todo1.interfaces.IKardexService;
import ar.com.todo1.repositories.KardexRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

/*** @author Andres Gonzalez ***/

@Log
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KardexServiceImpl implements IKardexService{
	
	private final KardexRepository kardexRepository;
	
	@Override
	public Kardex getKardex(Integer id) {
		return kardexRepository.findById(id).get() ;
	}	
	
	@Override
	public List<Kardex> listKardexs() {
		return kardexRepository.findAll();
	}

	@Override
	public List<Kardex> listKardexByProduct(Integer idProdcut) {
		return kardexRepository.findByIdProduct(idProdcut);
	}

	@Override
	public Boolean newKardexProduct(Kardex kardex) throws ProductException {
		Boolean response=Boolean.FALSE;
		try {
			kardex=kardexRepository.save(kardex);
			response=true;
		}  catch (Exception productException) {
			log.severe(productException.getMessage());
			throw productException;
		} 
		return response;
	}



}
