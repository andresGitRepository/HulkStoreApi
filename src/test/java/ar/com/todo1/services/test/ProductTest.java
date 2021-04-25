package ar.com.todo1.services.test;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ar.com.todo1.entities.Kardex;
import ar.com.todo1.entities.Product;
import ar.com.todo1.exceptions.StoreException;
import ar.com.todo1.models.ProductModel;
import ar.com.todo1.repositories.KardexRepository;
import ar.com.todo1.repositories.ProductRepository;
import ar.com.todo1.services.KardexServiceImpl;
import ar.com.todo1.services.ProductServiceImpl;

public class ProductTest {

	@Mock
	private ProductRepository productRepository;
	@Mock
	private KardexRepository kardexRepository;
	@InjectMocks
	private ProductServiceImpl productService;
	@InjectMocks
	private KardexServiceImpl kardexService;

	private Product product;
	private ProductModel productModel;
	private Kardex kardex;

	@SuppressWarnings("deprecation")
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		kardexService = new KardexServiceImpl(kardexRepository);
		productService = new ProductServiceImpl(productRepository, kardexService);

		product.setId(4001);
		product.setDescription("Tasa Capitan America");
		product.setStock(new BigInteger("10"));
		product.setPrice(new BigInteger("355"));

		kardex.setId(1);
		kardex.setIdProduct(4001);
		kardex.setDescription("STOCK INICIAL");
		kardex.setDate(new Date());
		kardex.setCount(new BigInteger("10"));

		Mockito.when(productRepository.save(product)).thenReturn(product);
		Mockito.when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
	}

	@Test
	public void findTest() throws StoreException {
		String expected = "Tasa Capitan America";
		Product response = productService.searchProduct(4001);
		assertEquals(expected, response.getDescription());
	}

	@Test
	public void newProducTest() {
		String expected = "Tasa Capitan America";
		try {
			Product response = new Product();// productService.newProduct(product);
			assertEquals(expected, response.getDescription());
		} catch (Exception e) {
			assert (Boolean.FALSE);
		}
	}

	@Test
	public void saleProductTest() {
		try {
			String expected = "Tasa Capitan America";
			productModel.setIdProduct(4001);
			productModel.setCount(new BigInteger("5"));
			Product response = new Product();// productService.saleProduct(productModel);
			assertEquals(expected, response.getDescription());
		} catch (Exception e) {
			assert (Boolean.FALSE);
		}
	}

	@Test
	public void buyProductTest() {
		try {
			String expected = "Tasa Capitan America";
			productModel.setIdProduct(4001);
			productModel.setCount(new BigInteger("20"));
			Product response = new Product();// productService.buyProduct(productModel);
			assertEquals(expected, response.getDescription());
		} catch (Exception e) {
			assert (Boolean.FALSE);
		}
	}

}
