package com.hulkstoreapi.interfaces;

import java.util.List;

import com.hulkstoreapi.entities.Kardex;

/*** @author Andres Gonzalez ***/

public interface IKardexService {
	public Kardex getKardex(Integer id);

	public List<Kardex> listKardexs();

	public List<Kardex> listKardexByProduct(Integer idProdcut);

	public Kardex saveKardex(Kardex kardex);
}