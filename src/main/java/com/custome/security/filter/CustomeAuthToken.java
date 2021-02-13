package com.custome.security.filter;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class CustomeAuthToken extends UsernamePasswordAuthenticationToken{

	public CustomeAuthToken(Object principal, Object credentials) {
		super(principal, credentials);	
	}

	public CustomeAuthToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
		// TODO Auto-generated constructor stub
	}

	
}
