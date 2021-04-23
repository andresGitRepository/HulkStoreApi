package com.hulkstoreapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hulkstoreapi.entities.Kardex;

/*** @author Andres Gonzalez ***/

@Repository
public interface KardexRepository extends CrudRepository<Kardex, Integer> {
	Optional<Kardex> findById(Integer id);

	@Query(value = "Select k from Kardex k order by k.idProduct,k.date")
	List<Kardex> findAll();

	@Query(value = "Select k from Kardex k where k.idProduct=?1 order by k.date")
	List<Kardex> findByIdProduct(Integer idProduct);
}
