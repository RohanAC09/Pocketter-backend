package com.rohan.user.dto.request;

public class EditUserRequest {
	
	private String username;
	private String fullName;
	private String bio;
	
	public String getFullName() {
		return fullName;
	}
	public String getBio() {
		return bio;
	}
	public String getUsername() {
		return username;
	}
}
