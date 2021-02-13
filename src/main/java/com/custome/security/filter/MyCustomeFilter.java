package com.custome.security.filter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Enumeration;
import java.util.stream.Collectors;

import javax.naming.AuthenticationException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
@Component
public class MyCustomeFilter implements Filter{

	@Autowired
	private AuthenticationManager authenticationManager;
	private Charset credentialsCharset = StandardCharsets.UTF_8;
	private byte[] decode(byte[] base64Token) {
		try {
			return Base64.getDecoder().decode(base64Token);
		}
		catch (IllegalArgumentException ex) {
			throw new BadCredentialsException("Failed to decode basic authentication token");
		}
	}
	protected Charset getCredentialsCharset(HttpServletRequest request) {
		return getCredentialsCharset();
	}
	public Charset getCredentialsCharset() {
		return this.credentialsCharset;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		String header = req.getHeader(HttpHeaders.AUTHORIZATION);
		if (header == null) {
			return;
		}
		header = header.trim();
		if (!StringUtils.startsWithIgnoreCase(header, "Basic")) {
			return;
		}
		if (header.equalsIgnoreCase("Basic")) {
			throw new BadCredentialsException("Empty basic authentication token");
		}
		byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
		byte[] decoded = decode(base64Token);
		String token = new String(decoded, getCredentialsCharset(req));
		int delim = token.indexOf(":");
		if (delim == -1) {
			throw new BadCredentialsException("Invalid basic authentication token");
		}
		String username=token.substring(0, delim);
		String password=token.substring(delim + 1);
		System.err.println("p--->"+password);
		CustomeAuthToken customeAuthToken = new CustomeAuthToken(username, password);
			Authentication authenticate = authenticationManager.authenticate(customeAuthToken);
			if(authenticate.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(authenticate);
				chain.doFilter(request, response);
			}
		
	}

}
