package io.hades.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.hades.dto.Role;
import io.hades.dto.User;

public class CustomeUserDetailsWrapper implements UserDetails {

	private String userName;
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public CustomeUserDetailsWrapper(User user){
		this.userName = user.getUserName();
		this.password = user.getPassword();
		
		List<GrantedAuthority> auths = new ArrayList<>();
		for(Role role: user.getRoles()) {
			auths.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		this.authorities = auths;
	}
	
	private static final long serialVersionUID = 1L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
