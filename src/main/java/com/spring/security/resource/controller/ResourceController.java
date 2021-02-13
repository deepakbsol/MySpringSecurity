package com.spring.security.resource.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {

	@GetMapping("/welcome")
	public String getWelcomeMessage() {
		return "Welcome to Spring Security !!!";
	}
}
