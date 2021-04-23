package com.hulkstoreapi.interfaces;

import java.util.List;
import java.util.Optional;

import com.hulkstoreapi.entities.Product;

/*** @author Andres Gonzalez ***/

public interface IProductService {
	public Optional<Product> findById(Integer id);

	public List<Product> listProduct();

	public Product saveProduct(Product product);
}
