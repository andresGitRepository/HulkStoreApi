package ar.com.todo1.interfaces;

import java.util.List;

import ar.com.todo1.auth.entities.CustomUser;
import ar.com.todo1.entities.Product;
import ar.com.todo1.exceptions.StoreException;
import ar.com.todo1.models.ProductModel;

/*** @author Andres Gonzalez ***/

public interface IProductService {
	public List<Product> searchProducts() throws StoreException;

	public Product searchProduct(Integer idProduct) throws StoreException;

	public Product newProduct(Product product, CustomUser user) throws StoreException;

	public Product saveProduct(Product product, CustomUser user) throws StoreException;

	public Product deleteProduct(Product product, CustomUser user) throws StoreException;

	public Product buyProduct(ProductModel productModel, CustomUser user) throws StoreException;

	public Product saleProduct(ProductModel productModel, CustomUser user) throws StoreException;

	public Boolean hasStock(ProductModel productModel) throws StoreException;

	public Boolean hasAvaibleStock(ProductModel productModel) throws StoreException;
}
