package io.hades.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import io.hades.persistence.IUserRepository;

@Configuration
@EnableWebSecurity
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	//configure who can access what based on role etc.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.httpBasic()
		.and()
		.authorizeRequests()
		.anyRequest()
		.authenticated();
		
		http
		.csrf().disable();
	}

	//https://www.javainterviewpoint.com/spring-boot-security-database-authentication-example/
	//configure the authentication based on User and permission granted as per role. Role is configured in wrapper
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth, IUserRepository userRepository) throws Exception {
		
		auth.userDetailsService(new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				return new CustomeUserDetailsWrapper(userRepository.findByUserName(username).get());
			}
		});
		
		/*
		 * auth.jdbcAuthentication().dataSource(dataSource)
		 * .authoritiesByUsernameQuery("select user_name,roles from user where user_name=?"
		 * )
		 * .usersByUsernameQuery("select user_name,password from user where user_name=?"
		 * );
		 */
	}

}
