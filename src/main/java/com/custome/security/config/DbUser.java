package com.custome.security.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class DbUser implements UserDetails{
	private UserForValidation userForValidation;
	public DbUser(UserForValidation userForValidation) {
		this.userForValidation=userForValidation;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		ArrayList<GrantedAuthority> authority = new ArrayList<GrantedAuthority>();
		authority.add(new SimpleGrantedAuthority("read"));
		return authority;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userForValidation.getPassword();
	}

	@Override
	public String toString() {
		return "DbUser [userForValidation=" + userForValidation + "]";
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userForValidation.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
