package com.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthServiceLayer implements UserDetailsService{

	private AuthUserDao authUserDao;
	@Autowired
	public void setAuthUserDAo(AuthUserDao authUserDao) {
		this.authUserDao=authUserDao;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("username=="+username);
		
		UserEntity user = authUserDao.getUserEntity(username);
		
		return new ValidUserDetails(user);
	}

}
