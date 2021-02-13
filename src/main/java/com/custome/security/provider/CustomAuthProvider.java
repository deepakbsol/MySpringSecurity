package com.custome.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.custome.security.filter.CustomeAuthToken;
@Component
public class CustomAuthProvider implements AuthenticationProvider{

	private UserDetailsService userDetailsService;
	private PasswordEncoder passwordEncoder;
	@Autowired
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username=authentication.getName();
		String password=(String)authentication.getCredentials();
		UserDetails DbUser = userDetailsService.loadUserByUsername(username);
		if(DbUser !=null && passwordEncoder.matches(password, DbUser.getPassword())) {
			//CustomeAuthToken customeAuthToken = new CustomeAuthToken(username, password);
			CustomeAuthToken customeAuthToken = new CustomeAuthToken(username, password, DbUser.getAuthorities());
			//customeAuthToken.setAuthenticated(true);
			return customeAuthToken;
		}

		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return CustomeAuthToken.class.equals(authentication);
	}

}
