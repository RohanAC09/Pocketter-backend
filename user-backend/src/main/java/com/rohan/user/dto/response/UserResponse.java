package com.rohan.user.dto.response;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserResponse {
	private String message;
	private Timestamp timeStamp;
	
	public String getMessage() {
		return message;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	
	@Override
	public String toString() {
		return "{ \"message\" : \"" + message + "\", \"timeStamp\" : \"" + timeStamp + "\" }";
	}
}
