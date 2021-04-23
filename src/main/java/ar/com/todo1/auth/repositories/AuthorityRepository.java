package ar.com.todo1.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.todo1.auth.entities.Authority;

/*** @author Andres Gonzalez ***/

@Repository("authorityRepository")
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
	Authority findByAuthority(String authority);
}
