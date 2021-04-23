package com.hulkstoreapi.services.test;

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

import com.hulkstoreapi.entities.Kardex;
import com.hulkstoreapi.entities.Product;
import com.hulkstoreapi.exceptions.ProductException;
import com.hulkstoreapi.models.ProductModel;
import com.hulkstoreapi.repositories.KardexRepository;
import com.hulkstoreapi.repositories.ProductRepository;
import com.hulkstoreapi.services.KardexServiceImpl;
import com.hulkstoreapi.services.ProductServiceImpl;

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
	public void findTest() {
		String expected = "Tasa Capitan America";
		Optional<Product> response = productService.findById(4001);
		assertEquals(expected, response.get().getDescription());
	}

	@Test
	public void newProducTest() {
		String expected = "Tasa Capitan America";
		try {
			Product response = productService.newProduct(product);
			assertEquals(expected, response.getDescription());
		} catch (ProductException e) {
			assert (Boolean.FALSE);
		}
	}

	@Test
	public void saleProductTest() {
		try {
			String expected = "Tasa Capitan America";
			productModel.setIdProduct(4001);
			productModel.setDate(new Date());
			productModel.setReason("VENTA EN LOCAL");
			productModel.setCount(new BigInteger("5"));
			Product response = productService.saleProduct(productModel);
			assertEquals(expected, response.getDescription());
		} catch (ProductException e) {
			assert (Boolean.FALSE);
		}
	}

	@Test
	public void buyProductTest() {
		try {
			String expected = "Tasa Capitan America";
			productModel.setIdProduct(4001);
			productModel.setDate(new Date());
			productModel.setReason("COMPRA A PROVEEDOR");
			productModel.setCount(new BigInteger("20"));
			Product response = productService.buyProduct(productModel);
			assertEquals(expected, response.getDescription());
		} catch (ProductException e) {
			assert (Boolean.FALSE);
		}
	}

}
