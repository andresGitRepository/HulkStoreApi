package ar.com.todo1.interfaces;

import java.util.List;
import java.util.Optional;

import ar.com.todo1.entities.Product;
import ar.com.todo1.exceptions.StoreException;
import ar.com.todo1.models.ProductModel;

/*** @author Andres Gonzalez ***/

public interface IProductService {
	public Optional<Product> findById(Integer id);

	public List<Product> listProduct();

	public Product newProduct(Product product) throws StoreException;
	
	public Product saveProduct(Product product) throws StoreException;

	public Product buyProduct(ProductModel productModel) throws StoreException;

	public Product saleProduct(ProductModel productModel) throws StoreException;

	public Boolean hasStock(ProductModel productModel) throws StoreException;

	public Boolean hasAvaibleStock(ProductModel productModel) throws StoreException;
}
