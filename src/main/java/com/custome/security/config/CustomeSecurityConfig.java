package com.custome.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.custome.security.filter.MyCustomeFilter;
import com.custome.security.provider.CustomAuthProvider;
@Configuration
public class CustomeSecurityConfig extends WebSecurityConfigurerAdapter{

	private MyCustomeFilter filter;
	private CustomAuthProvider provider;
	@Autowired
	public MyCustomeFilter setMyCustomeFilter(MyCustomeFilter filter) {
		return this.filter=filter;
	}
	@Autowired
	public CustomAuthProvider setCustomAuthProvider(CustomAuthProvider provider) {
		return this.provider=provider;
	}
	@Bean
	public UserDetailsService getUDetailsService() {
		return new CustomeUserDetailsService();
	}
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(provider);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterAt(filter,BasicAuthenticationFilter.class);
		http.authorizeRequests().anyRequest().permitAll();
	}

}
