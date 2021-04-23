package com.hulkstoreapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hulkstoreapi.entities.Product;

/*** @author Andres Gonzalez ***/

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	Optional<Product> findById(Integer id);
}
