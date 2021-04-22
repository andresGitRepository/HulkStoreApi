package com.hulkstoreapi.auth.servicies;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hulkstoreapi.auth.entities.Authority;
import com.hulkstoreapi.auth.entities.User;
import com.hulkstoreapi.auth.interfaces.IUserService;
import com.hulkstoreapi.auth.repositories.AuthorityRepository;
import com.hulkstoreapi.auth.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

/*** @author Andres Gonzalez ***/

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
	public User saveUser(User user) {
		user.setEnabled(Boolean.TRUE);
		Authority authority = authorityRepository.findByAuthority("ROLE_USER");
		Set<Authority> userAuthorityList = new HashSet<Authority>(Arrays.asList(authority));
		user.setAuthorities(userAuthorityList);
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(5);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
}