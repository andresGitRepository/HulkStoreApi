package com.hulkstoreapi.auth.interfaces;

import java.util.Optional;

import com.hulkstoreapi.auth.entities.User;

/*** @author Andres Gonzalez ***/

public interface IUserService {
	public Optional<User> findByEmail(String username);

	public User saveUser(User user);
}
