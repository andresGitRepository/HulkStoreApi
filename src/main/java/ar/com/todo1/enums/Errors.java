package ar.com.todo1.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*** @author Andres Gonzalez ***/

@Getter
@AllArgsConstructor
public enum Errors {
	PRODUCT_NEW("001", "Error al guardar producto nuevo."), 
	PRODUCT_SAVE("002", "Error al guardar producto."), 
	PRODUCT_SALE("003", "Error al vender prodcuto."),
	PRODUCT_BUY("004", "Error al comprar producto."),
	PRODUCT_DELETE("005", "Error al borrar producto."),
	PRODUCT_OUT_OF_STOCK("006", "Sin stock en el producto."),
	PRODUCT_NO_STOCK_AVAILABLE("007", "Sin stock disponible."),
	KARDEX_SAVE("008", "Error al guardar kardex.");

	private String code;
	private String description;
}