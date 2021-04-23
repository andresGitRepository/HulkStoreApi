package ar.com.todo1.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*** @author Andres Gonzalez ***/

@Getter
@AllArgsConstructor
public enum Errors {
	PRODUCT_SAVE("001", "Error al guardar producto."), 
	PRODUCT_SALE("002", "Error al vender prodcuto."),
	PRODUCT_BUY("003", "Error al comprar producto."),
	PRODUCT_OUT_OF_STOCK("004", "Sin stock en el producto."),
	PRODUCT_NO_STOCK_AVAILABLE("005", "Sin stock disponible."),
	KARDEX_SAVE("006", "Error al guardar kardex.");

	private String code;
	private String description;
}