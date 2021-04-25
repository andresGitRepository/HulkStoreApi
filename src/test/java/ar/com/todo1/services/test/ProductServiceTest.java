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

import ar.com.todo1.auth.entities.CustomUser;
import ar.com.todo1.entities.Kardex;
import ar.com.todo1.entities.Product;
import ar.com.todo1.exceptions.StoreException;
import ar.com.todo1.models.ProductModel;
import ar.com.todo1.repositories.KardexRepository;
import ar.com.todo1.repositories.ProductRepository;
import ar.com.todo1.services.KardexServiceImpl;
import ar.com.todo1.services.ProductServiceImpl;

public class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;
	@Mock
	private KardexRepository kardexRepository;
	@InjectMocks
	private ProductServiceImpl productService;
	@InjectMocks
	private KardexServiceImpl kardexService;
	
	//PRODUCT
	private Product product = Product.builder().build();
	private ProductModel productModel;
	private final Integer ID_PRODUCT = 1001;
	private final String DESCRIPTIONS = "Puño Hulk";
	
	//CUSTOM USER
	private CustomUser user = new CustomUser(null, null, null, 1, null);
	
	//KARDEX
	private Kardex kardex = Kardex.builder().build();

	@SuppressWarnings("deprecation")
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		kardexService = new KardexServiceImpl(kardexRepository);
		productService = new ProductServiceImpl(productRepository, kardexService);
		
		product = Product.builder()
					.id(ID_PRODUCT)
					.description(DESCRIPTIONS)
					.stock(new BigInteger("10"))
					.price(new BigInteger("355")).build();

		kardex = Kardex.builder()
				.id(1)
				.idProduct(ID_PRODUCT)
				.description("STOCK INICIAL")
				.date(new Date())
				.count(new BigInteger("10")).build();

		Mockito.when(productRepository.save(product)).thenReturn(product);
		Mockito.when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
	}

	@Test
	public void findTest() throws StoreException {
		Integer expected = ID_PRODUCT;
		Optional<Product> response = productService.searchProduct(ID_PRODUCT);
		assertEquals(expected, response.get().getId());
	}

	@Test
	public void newProducTest() {
		Integer expected = ID_PRODUCT;
		try {
			Product response = productService.newProduct(product,user);
			assertEquals(expected, response.getId());
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
