package ar.com.todo1.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*** @author Andres Gonzalez ***/

@Getter
@AllArgsConstructor
public enum Errors {
	PRODUCT_NEW("101", "Error al guardar producto nuevo."), PRODUCT_SAVE("102", "Error al guardar producto."),
	PRODUCT_SALE("103", "Error al vender producto."), PRODUCT_BUY("104", "Error al comprar producto."),
	PRODUCT_DELETE("105", "Error al borrar producto."), PRODUCT_OUT_OF_STOCK("106", "Sin stock en el producto."),
	PRODUCT_NO_STOCK_AVAILABLE("107", "Sin stock disponible."), PRODUCT_SEARCH("108", "Error al buscar producto."),
	KARDEX_SAVE("201", "Error al guardar kardex."), KARDEX_SEARCH("202", "Error al buscar kardex."),
	USER_SAVE("301", "Error al guardar usuario.");

	private String code;
	private String description;
}