package com.hulkstoreapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hulkstoreapi.entities.Kardex;
import com.hulkstoreapi.interfaces.IKardexService;
import com.hulkstoreapi.repositories.KardexRepository;

import lombok.RequiredArgsConstructor;

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
	public Kardex saveKardex(Kardex kardex) {
		return kardexRepository.save(kardex);
	}



}
