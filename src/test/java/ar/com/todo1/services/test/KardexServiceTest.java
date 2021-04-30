package ar.com.todo1.services.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.joda.time.Instant;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ar.com.todo1.entities.Kardex;
import ar.com.todo1.enums.KardexType;
import ar.com.todo1.exceptions.StoreException;
import ar.com.todo1.models.ProductModel;
import ar.com.todo1.repositories.KardexRepository;
import ar.com.todo1.services.KardexServiceImpl;

public class KardexServiceTest {

	private static final Integer ID = 1;
	private Optional<Kardex> KARDEX = Optional.of(new Kardex());
	private Kardex KARDEX_INSERT = new Kardex();
	private ProductModel PRODUCT_MODEL = new ProductModel();
	private List<Kardex> LIST_KARDEX = new ArrayList<Kardex>();
	private static final Integer ID_PRODUCT = 1003;
	private static final Date DATE = Instant.now().toDate();
	private static final String DESCRIPTION = KardexType.INICIAL.getDescription();
	private static final BigInteger COUNT = BigInteger.ZERO;
	private static final BigInteger COUNT_BUY = BigInteger.valueOf(1000);

	@Mock
	private KardexRepository kardexRepository;

	@InjectMocks
	private KardexServiceImpl kardexServiceImpl;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		KARDEX = Optional.of(
				Kardex.builder().id(ID).idProduct(ID_PRODUCT).description(DESCRIPTION).date(DATE).count(COUNT).build());
		KARDEX_INSERT = Kardex.builder().id(null).idProduct(ID_PRODUCT).description(KardexType.COMPRA.getDescription())
				.date(DATE).count(COUNT_BUY).build();

		LIST_KARDEX.add(KARDEX.get());
		LIST_KARDEX.add(KARDEX_INSERT);

		PRODUCT_MODEL = ProductModel.builder().idProduct(ID_PRODUCT).count(COUNT_BUY).build();

		Mockito.when(kardexRepository.findById(ID)).thenReturn(KARDEX);
		Mockito.when(kardexRepository.findAll()).thenReturn(LIST_KARDEX);
		Mockito.when(kardexRepository.findByIdProduct(ID_PRODUCT)).thenReturn(LIST_KARDEX);
		Mockito.when(kardexRepository.save((Kardex) any())).thenReturn(KARDEX_INSERT);
	}

	@Test
	public void insertKardexTest() throws StoreException {
		Kardex responseBuy = kardexServiceImpl.insertKardex(PRODUCT_MODEL, KardexType.COMPRA);
		Kardex responseSale = kardexServiceImpl.insertKardex(PRODUCT_MODEL, KardexType.VENTA);
		assertEquals(responseBuy, KARDEX_INSERT);
		assertEquals(responseSale, KARDEX_INSERT);
	}

	@Test
	public void searchKardexProductTest() throws StoreException {
		List<Kardex> response = kardexServiceImpl.searchKardexProduct(ID_PRODUCT);
		assertEquals(response, LIST_KARDEX);
	}

	@Test
	public void searchKardexsTest() throws StoreException {
		List<Kardex> response = kardexServiceImpl.searchKardexs();
		assertEquals(response, LIST_KARDEX);
	}

	@Test
	public void getKardexTest() {
		Kardex response = kardexServiceImpl.getKardex(ID);
		assertEquals(response, KARDEX.get());
	}
}
