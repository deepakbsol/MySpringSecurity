package com.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig {
	
	@Bean
	public UserDetailsService getUserDetailsService() {
		return new AuthServiceLayer();
	}
	@Bean
	public PasswordEncoder getpasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
