package com.hulkstoreapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hulkstoreapi.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
