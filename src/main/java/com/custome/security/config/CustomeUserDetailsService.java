package com.custome.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomeUserDetailsService implements UserDetailsService{

	private CustomeUserDetailsDao customeUserDetailsDao;
	@Autowired
	public void setCustomeUserDetailsDao(CustomeUserDetailsDao customeUserDetailsDao) {
		this.customeUserDetailsDao = customeUserDetailsDao;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserForValidation dbUser=customeUserDetailsDao.getUserForValidation(username);
		return new DbUser(dbUser);
	}

}
