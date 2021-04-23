package ar.com.todo1.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.todo1.entities.Product;

/*** @author Andres Gonzalez ***/

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	Optional<Product> findById(Integer id);
}
