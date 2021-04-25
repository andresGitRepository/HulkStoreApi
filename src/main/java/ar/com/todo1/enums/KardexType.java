package ar.com.todo1.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*** @author Andres Gonzalez ***/

@Getter
@AllArgsConstructor
public enum KardexType {
	INICIAL((short) 1, "STOCK INICIAL"), COMPRA((short) 2, "COMPRA DE PRODUCTO"), VENTA((short) 3, "VENTA DE PRODUCTO"), BORRADO((short) 4, "BORRADO DE PRODUCTO");

	private Short code;
	private String description;
}