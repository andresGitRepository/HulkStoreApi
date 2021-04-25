package ar.com.todo1.auth.services.test;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ar.com.todo1.auth.entities.User;
import ar.com.todo1.auth.repositories.AuthorityRepository;
import ar.com.todo1.auth.repositories.UserRepository;
import ar.com.todo1.auth.servicies.UserServiceImpl;
import ar.com.todo1.exceptions.StoreException;

public class UserServiceTest {

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
	public void saveTest() throws StoreException {
		String email = "admin@admin.com";
		User result = userServiceImpl.saveUser(user);
		assertEquals(email, result.getEmail());
	}

}
