package com.spring.security.config;

import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserDao {

	public UserEntity getUserEntity(String username);
}
