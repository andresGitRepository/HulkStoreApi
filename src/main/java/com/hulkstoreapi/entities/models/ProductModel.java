package com.hulkstoreapi.entities.models;

import java.math.BigInteger;
import java.util.Date;

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
	private Date date;
	private String reason;
	private BigInteger count;

}