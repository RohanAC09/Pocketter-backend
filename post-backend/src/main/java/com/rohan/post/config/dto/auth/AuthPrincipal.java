package com.rohan.post.config.dto.auth;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthPrincipal {
	
	private String username;
	private String jwt;
	
	public String getUsername() {
		return username;
	}
	public String getJwt() {
		return jwt;
	}
}
