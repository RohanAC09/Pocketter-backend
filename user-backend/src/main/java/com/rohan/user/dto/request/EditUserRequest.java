package com.rohan.user.dto.request;

import org.springframework.stereotype.Component;

@Component
public class EditUserRequest {
	
	private String fullName;
	private String bio;
	
	public String getFullName() {
		return fullName;
	}
	public String getBio() {
		return bio;
	}
}
