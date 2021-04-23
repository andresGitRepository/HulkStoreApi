package ar.com.todo1.auth.config;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import ar.com.todo1.auth.entities.CustomUser;
import ar.com.todo1.auth.entities.User;
import ar.com.todo1.auth.interfaces.IUserService;

/*** @author Andres Gonzalez ***/

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	IUserService userservice;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String email = authentication.getName();
		String password = authentication.getCredentials().toString();

		User user = userservice.findByEmail(email)
				.orElseThrow(() -> new BadCredentialsException("Usuario no existente"));
		if (BCrypt.checkpw(password, user.getPassword())) {
			Set<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
					.map(p -> new SimpleGrantedAuthority(p.getAuthority())).collect(Collectors.toSet());
			return new CustomUser(user.getEmail(), user.getPassword(), user.getAge(),user.getId(), grantedAuthorities);
		} else {
			throw new BadCredentialsException("Contrasena es incorrecta");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
