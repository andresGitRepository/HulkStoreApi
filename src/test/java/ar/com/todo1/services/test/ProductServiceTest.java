package ar.com.todo1.services.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.joda.time.Instant;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ar.com.todo1.auth.entities.CustomUser;
import ar.com.todo1.entities.Product;
import ar.com.todo1.enums.KardexType;
import ar.com.todo1.exceptions.StoreException;
import ar.com.todo1.interfaces.IKardexService;
import ar.com.todo1.models.ProductModel;
import ar.com.todo1.repositories.ProductRepository;
import ar.com.todo1.services.ProductServiceImpl;

//TEST JUNIT 4
public class ProductServiceTest {
	private CustomUser USER = new CustomUser(null, null, null, 1, null);
	private Optional<Product> PRODUCT = Optional.of(new Product());
	private Optional<Product> PRODUCT_NULL = Optional.of(new Product());
	private List<Product> LIST_PRODUCT = new ArrayList<Product>();
	private ProductModel PRODUCT_MODEL = new ProductModel();
	private static final Integer ID_PRODUCT = 1001;
	private static final Integer ID_PRODUCT2 = 1002;
	private static final String DESCRIPTIONS = "Puño Hulk";
	private static final BigInteger STOCK = new BigInteger("100");
	@SuppressWarnings("unused")
	private static final BigInteger STOCK_CERO = new BigInteger("0");
	private static final BigInteger PRICE = new BigInteger("355");
	private static final BigInteger COUNT = new BigInteger("5");
	@SuppressWarnings("unused")
	private static final BigInteger COUNT_EXCEEDS = new BigInteger("2500");

	@Mock
	private IKardexService iKardexService;
	@Mock
	private ProductRepository productRepository;
	@InjectMocks
	private ProductServiceImpl productService;

	@Before
	public void init() throws StoreException {
		MockitoAnnotations.initMocks(this);
		PRODUCT = Optional.of(Product.builder().id(ID_PRODUCT).description(DESCRIPTIONS).stock(STOCK).price(PRICE)
				.dateUnregistry(null).userUnregistry(null).userRegistry(USER.getId())
				.dateRegistry(Instant.now().toDate()).build());
		PRODUCT_NULL = Optional.of(Product.builder().id(BigInteger.ZERO.intValue()).description("No existe").build());

		LIST_PRODUCT.add(PRODUCT.get());

		PRODUCT_MODEL = ProductModel.builder().idProduct(ID_PRODUCT).count(COUNT).build();

		Mockito.when(iKardexService.insertKardex(PRODUCT_MODEL, KardexType.INICIAL)).thenReturn(null);
		Mockito.when(productRepository.save((Product) any())).thenReturn(PRODUCT.get());
		Mockito.when(productRepository.findById(ID_PRODUCT)).thenReturn(PRODUCT);
		Mockito.when(productRepository.findById(ID_PRODUCT2)).thenReturn(Optional.empty());
		Mockito.when(productRepository.findAll()).thenReturn(LIST_PRODUCT);
	}

	@Test
	public void searchProductsTest() throws StoreException {
		List<Product> response = productService.searchProducts();
		assertEquals(response, LIST_PRODUCT);
	}

	@Test
	public void searchProductTest() throws StoreException {
		Optional<Product> response = productService.searchProduct(ID_PRODUCT);
		Optional<Product> responseNull = productService.searchProduct(ID_PRODUCT2);
		assertEquals(response, PRODUCT);
		assertEquals(responseNull, PRODUCT_NULL);
	}

	@Test
	public void newProducTest() throws StoreException {
		Product response = productService.newProduct(PRODUCT.get(), USER);
		assertEquals(response, PRODUCT.get());
	}

	@Test
	public void buyProductTest() throws StoreException {
		Product response = productService.buyProduct(PRODUCT_MODEL, USER);
		assertEquals(response, PRODUCT.get());
	}

	@Test
	public void saleProductTest() throws StoreException {
		Product response = productService.saleProduct(PRODUCT_MODEL, USER);
		assertEquals(response, PRODUCT.get());
	}

//	@SuppressWarnings("unused")
//	@Test
//	public void hasAvaibleStockTest() throws StoreException {
//		PRODUCT_MODEL.setCount(COUNT_EXCEEDS);
//		Exception exception = productService.saleProduct(PRODUCT_MODEL, USER);
//		assertEquals(exception.getMessage(), Errors.PRODUCT_SALE.getDescription());
//	}
//	
//	@SuppressWarnings("unused")
//	@Test
//	public void hasStockTest() throws StoreException {
//		PRODUCT.get().setStock(STOCK_CERO);
//		Exception exception = productService.saleProduct(PRODUCT_MODEL, USER);
//		assertEquals(exception.getMessage(), Errors.PRODUCT_SALE.getDescription());
//	}

	@Test
	public void saveProductTest() throws StoreException {
		Product response = productService.saveProduct(PRODUCT.get(), USER);
		PRODUCT.get().setId(null);
		PRODUCT.get().setUserRegistry(null);
		PRODUCT.get().setDateRegistry(null);
		Product responseNew = productService.saveProduct(PRODUCT.get(), USER);
		assertEquals(response, PRODUCT.get());
		assertEquals(responseNew, PRODUCT.get());
	}

	@Test
	public void deleteProductTest() throws StoreException {
		Product response = productService.deleteProduct(PRODUCT.get(), USER);
		assertEquals(response, PRODUCT.get());
	}
}
