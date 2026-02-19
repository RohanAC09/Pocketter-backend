package com.rohan.user.dto.response;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
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
}
