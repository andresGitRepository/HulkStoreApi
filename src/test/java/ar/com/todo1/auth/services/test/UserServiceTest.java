package ar.com.todo1.auth.services.test;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ar.com.todo1.auth.entities.User;
import ar.com.todo1.auth.repositories.AuthorityRepository;
import ar.com.todo1.auth.repositories.UserRepository;
import ar.com.todo1.auth.servicies.UserServiceImpl;
import ar.com.todo1.exceptions.StoreException;

public class UserServiceTest {
	private Optional<User> USER = Optional.of(new User());
	private Optional<User> USER2 = Optional.of(new User());
	private static final Integer USER_ID = Integer.valueOf(9999);
	private static final String NAME = "Administrador";
	private static final String LAST_NAME = "ADM";
	private static final String EMAIL = "admin@admin.com";
	private static final String EMAIL2 = "admin@admin.com.ar";
	private static final String PASSWORD = "123456";

	@Mock
	private UserRepository userRepository;
	@Mock
	private AuthorityRepository authorityRepository;
	@InjectMocks
	private UserServiceImpl userServiceImpl;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		USER = Optional
				.of(User.builder().id(USER_ID).name(NAME).lastName(LAST_NAME).email(EMAIL).password(PASSWORD).build());

		USER2 = Optional
				.of(User.builder().id(USER_ID).name(NAME).lastName(LAST_NAME).email(EMAIL2).password(PASSWORD).build());

		Mockito.when(userRepository.save(USER.get())).thenReturn(USER.get());
		Mockito.when(userRepository.save(USER2.get())).thenReturn(null);
		Mockito.when(userRepository.findByEmail(EMAIL)).thenReturn(USER);
	}

	@Test
	public void saveUserTest() throws StoreException {
		User response = userServiceImpl.saveUser(USER.get());
		assertEquals(response, USER.get());
	}

	@Test
	public void saveUserErrorTest() throws StoreException {
		User response = userServiceImpl.saveUser(USER2.get());
		assertEquals(response, null);
	}

	@Test
	public void findByEmailTest() {
		Optional<User> response = userServiceImpl.findByEmail(EMAIL);
		assertEquals(response, USER);
	}

}
