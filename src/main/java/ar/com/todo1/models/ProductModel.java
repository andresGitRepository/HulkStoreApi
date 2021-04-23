package ar.com.todo1.models;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*** @author Andres Gonzalez ***/

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductModel {
	private Integer idProduct;
	private BigInteger count;
}