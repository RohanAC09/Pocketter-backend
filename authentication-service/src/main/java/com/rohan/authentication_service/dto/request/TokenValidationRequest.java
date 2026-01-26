package com.rohan.authentication_service.dto.request;

public class TokenValidationRequest {
	String token;
	
	public TokenValidationRequest(String token) {
		this.token=token;
	}

	public String getToken() {
		return token;
	}
}
