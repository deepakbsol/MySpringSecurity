package com.custome.security.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resource")
public class TestResourceServer {

	@GetMapping("/welcome")
	public String welcomeMessage() {
		return "Welcome to custome spring security application resource !!!";
	}
}
