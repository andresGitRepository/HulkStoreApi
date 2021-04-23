package com.hulkstoreapi.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hulkstoreapi.auth.entities.Authority;

/*** @author Andres Gonzalez ***/

@Repository("authorityRepository")
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
	Authority findByAuthority(String authority);
}
