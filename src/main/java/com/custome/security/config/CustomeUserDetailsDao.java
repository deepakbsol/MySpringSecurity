package com.custome.security.config;

import org.springframework.stereotype.Repository;

@Repository
public interface CustomeUserDetailsDao {

	public UserForValidation getUserForValidation(String username);

}
