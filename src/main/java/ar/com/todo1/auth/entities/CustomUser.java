package ar.com.todo1.auth.entities;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;

/*** @author Andres Gonzalez ***/

@Getter
public class CustomUser extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 2799269586911067020L;
	private final Integer age;
	private final Integer id;

	public CustomUser(String username, String password, Integer age, Integer id,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.age = age;
		this.id=id;
	}
}
