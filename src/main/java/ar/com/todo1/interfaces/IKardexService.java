package ar.com.todo1.interfaces;

import java.util.List;

import ar.com.todo1.entities.Kardex;
import ar.com.todo1.exceptions.ProductException;

/*** @author Andres Gonzalez ***/

public interface IKardexService {
	public Kardex getKardex(Integer id);

	public List<Kardex> listKardexs() throws ProductException;

	public List<Kardex> listKardexByProduct(Integer idProdcut) throws ProductException;

	public Boolean newKardexProduct(Kardex kardex) throws ProductException ;
}