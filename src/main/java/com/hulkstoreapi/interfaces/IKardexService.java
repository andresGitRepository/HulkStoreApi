package com.hulkstoreapi.interfaces;

import java.util.List;

import com.hulkstoreapi.entities.Kardex;
import com.hulkstoreapi.exceptions.ProductException;

/*** @author Andres Gonzalez ***/

public interface IKardexService {
	public Kardex getKardex(Integer id);

	public List<Kardex> listKardexs() throws ProductException;

	public List<Kardex> listKardexByProduct(Integer idProdcut) throws ProductException;

	public Boolean newKardexProduct(Kardex kardex) throws ProductException ;
}