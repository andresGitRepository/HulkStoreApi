package ar.com.todo1.auth.interfaces;

import java.util.Optional;

import ar.com.todo1.auth.entities.User;
import ar.com.todo1.exceptions.StoreException;

/*** @author Andres Gonzalez ***/

public interface IUserService {
	public Optional<User> findByEmail(String username);

	public User saveUser(User user) throws StoreException;
}
