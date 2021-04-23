package com.hulkstoreapi.auth.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hulkstoreapi.auth.entities.User;

/*** @author Andres Gonzalez ***/

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User> findByEmail(String email);
}