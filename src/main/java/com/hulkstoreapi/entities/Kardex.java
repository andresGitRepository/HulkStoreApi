package com.hulkstoreapi.entities;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*** @author Andres Gonzalez ***/

@Data
@Builder
@NoArgsConstructor 
@AllArgsConstructor
@Entity
@Table(name = "Kardex")
public class Kardex {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "kardex_id")
	private Integer id;
	@NotNull
    @Column(name = "product_id")
	private Integer idProduct;
	@NotNull
    @Column(name = "kardex_date")
	private Date date;	
	@NotNull
    @Column(name = "kardex_description")
	private String description;	
    @NotNull
    @Column(name = "kardex_counter")
    private BigInteger count;
    
}