package ar.com.todo1.auth.servicies;



import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ar.com.todo1.auth.entities.Authority;
import ar.com.todo1.auth.entities.User;
import ar.com.todo1.auth.interfaces.IUserService;
import ar.com.todo1.auth.repositories.AuthorityRepository;
import ar.com.todo1.auth.repositories.UserRepository;
import ar.com.todo1.enums.Errors;
import ar.com.todo1.exceptions.StoreException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

/*** @author Andres Gonzalez ***/

@Log
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements IUserService {

	private final UserRepository userRepository;
	private final AuthorityRepository authorityRepository;

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User saveUser(User user) throws StoreException{
			try {
			user.setEnabled(Boolean.TRUE);
			Authority authority = authorityRepository.findByAuthority("ROLE_USER");
			Set<Authority> userAuthorityList = new HashSet<Authority>(Arrays.asList(authority));
			user.setAuthorities(userAuthorityList);
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(5);
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			return userRepository.save(user);
		}catch (Exception exception) {
			StoreException storeException = new StoreException(exception, Errors.USER_SAVE.getCode(),
					Errors.USER_SAVE.getDescription());
			log.severe(String.join(" ", storeException.getCode(), storeException.getDescription(),
					storeException.getLocalizedMessage()));
			throw storeException;
		}
	}
}