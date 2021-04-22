package com.hulkstoreapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hulkstoreapi.entities.Product;
import com.hulkstoreapi.interfaces.IProductService;
import com.hulkstoreapi.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements IProductService {
	private final ProductRepository iProductRepository;

	public List<Product> listProduct() {
		return iProductRepository.findAll();
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return iProductRepository.findById(id);
	}
}
