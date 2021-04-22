package com.hulkstoreapi.auth.test;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hulkstoreapi.auth.entities.User;
import com.hulkstoreapi.auth.repositories.AuthorityRepository;
import com.hulkstoreapi.auth.repositories.UserRepository;
import com.hulkstoreapi.auth.servicies.UserServiceImpl;

public class UserTest {

	@Mock
	private UserRepository userRepository;
	@Mock
	private AuthorityRepository authorityRepository;

	private UserServiceImpl userServiceImpl;
	private User user = new User();

	@SuppressWarnings("deprecation")
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		userServiceImpl = new UserServiceImpl(userRepository, authorityRepository);
		user.setId(9999);
		user.setName("Administrador");
		user.setLastName("");
		user.setEmail("admin@admin.com");
		user.setPassword("123456");

		Mockito.when(userRepository.save(user)).thenReturn(user);
		Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
	}

	@Test
	public void findTest() {
		String email = "admin@admin.com";
		Optional<User> result = userServiceImpl.findByEmail(email);
		assertEquals(email, result.get().getEmail());
	}

	@Test
	public void saveTest() {
		String email = "admin@admin.com";
		User result = userServiceImpl.saveUser(user);
		assertEquals(email, result.getEmail());
	}

}
