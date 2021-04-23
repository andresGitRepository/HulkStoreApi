package ar.com.todo1.interfaces;

import java.util.List;

import ar.com.todo1.entities.Kardex;
import ar.com.todo1.enums.KardexType;
import ar.com.todo1.exceptions.StoreException;
import ar.com.todo1.models.ProductModel;

/*** @author Andres Gonzalez ***/

public interface IKardexService {
	public Kardex getKardex(Integer id);

	public List<Kardex> listKardexs() throws StoreException;

	public List<Kardex> listKardexByProduct(Integer idProdcut) throws StoreException;

	public Kardex insertKardex(ProductModel productModel, KardexType type) throws StoreException;
}