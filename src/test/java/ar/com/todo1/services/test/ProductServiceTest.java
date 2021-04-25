package ar.com.todo1.services.test;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
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
import ar.com.todo1.interfaces.IComplementABM;
import ar.com.todo1.models.ProductModel;
import ar.com.todo1.repositories.KardexRepository;
import ar.com.todo1.repositories.ProductRepository;
import ar.com.todo1.services.KardexServiceImpl;
import ar.com.todo1.services.ProductServiceImpl;

//TEST JUNIT 4
public class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;
	@Mock
	private KardexRepository kardexRepository;
	@InjectMocks
	private ProductServiceImpl productService;
	@InjectMocks
	private KardexServiceImpl kardexService;

	// PRODUCT
	private Product product = Product.builder().build();
	private ProductModel productModel = ProductModel.builder().build();
	private final Integer ID_PRODUCT = 1001;
	private final String DESCRIPTIONS = "Puño Hulk";
	private final BigInteger STOCK = new BigInteger("10");
	private final BigInteger PRICE = new BigInteger("355");
	private final BigInteger COUNT = new BigInteger("5");

	// CUSTOM USER
	private CustomUser user = new CustomUser(null, null, null, 1, null);

	@SuppressWarnings("deprecation")
	@Before
	public void init() throws StoreException {
		MockitoAnnotations.initMocks(this);
		kardexService = new KardexServiceImpl(kardexRepository);
		productService = new ProductServiceImpl(productRepository, kardexService);

		product = Product.builder().id(ID_PRODUCT).description(DESCRIPTIONS).stock(STOCK).price(PRICE).build();

		Mockito.when(productService.buyProduct(productModel, user)).thenReturn(product);
		Mockito.when(productService.saleProduct(productModel, user)).thenReturn(product);
		Mockito.when(kardexService.insertKardex(productModel, KardexType.INICIAL)).thenReturn(null);
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
	public void newProducTest() throws StoreException {
		product = saveProduct(product, user);
		ProductModel newProduct = ProductModel.builder().idProduct(product.getId()).count(product.getStock()).build();
		kardexService.insertKardex(newProduct, KardexType.INICIAL);
	}

	public Product saveProduct(Product product, CustomUser user) {
		IComplementABM complementABM = (object, id) -> {
			Product productABM = (Product) object;
			if (productABM.getId() == null) {
				return productABM.withUserRegistry(id).withDateRegistry(Instant.now().toDate());
			} else {
				return productABM.withUserModify(id).withDateModify(Instant.now().toDate());
			}
		};
		product = (Product) complementABM.execute(product, user.getId());
		return productRepository.save(product);

	}

	@Test
	public void saleProductTest() throws StoreException {
		String expected = DESCRIPTIONS;
		productModel.setIdProduct(ID_PRODUCT);
		productModel.setCount(COUNT);
		Product response = productService.saleProduct(productModel, user);
		assertEquals(expected, response.getDescription());
	}

	@Test
	public void buyProductTest() throws StoreException {
		String expected = DESCRIPTIONS;
		productModel.setIdProduct(ID_PRODUCT);
		productModel.setCount(COUNT);
		Product response = productService.buyProduct(productModel, user);
		assertEquals(expected, response.getDescription());
	}

}
