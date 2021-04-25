package ar.com.todo1.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationProvider provider;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(5);
		return bCryptPasswordEncoder;
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/h2-console", "/login/**", "/logout", "/signup", "/createUser", "/kardex",
						"/searchKardex","/allkardexs", "/products", "/buys", "/saes", "/searchProducts", "/newProducts",
						"/deleteProducts","/allproducts")
				.permitAll().antMatchers("/dist/**/**").permitAll().anyRequest().authenticated().and().headers()
				.frameOptions().disable().and().csrf().disable().formLogin().defaultSuccessUrl("/home", true)
				.loginPage("/login").permitAll().and().logout().logoutSuccessUrl("/login?logout=true")
				.invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll().and().exceptionHandling()
				.accessDeniedPage("/403");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(provider);
	}
}
