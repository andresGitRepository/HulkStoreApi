package ar.com.todo1.interfaces;

import java.util.List;
import java.util.Optional;

import ar.com.todo1.entities.Product;
import ar.com.todo1.exceptions.ProductException;
import ar.com.todo1.models.ProductModel;

/*** @author Andres Gonzalez ***/

public interface IProductService {
	public Optional<Product> findById(Integer id);

	public List<Product> listProduct();

	public Product newProduct(Product product) throws ProductException;

	public Product buyProduct(ProductModel product) throws ProductException;

	public Product saleProduct(ProductModel product) throws ProductException;

	public Boolean hasStock(ProductModel product) throws ProductException;

	public Boolean hasAvaibleStock(ProductModel product) throws ProductException;
}
