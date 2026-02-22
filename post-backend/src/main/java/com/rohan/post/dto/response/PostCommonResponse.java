package com.rohan.post.dto.response;

import java.sql.Timestamp;

public class PostCommonResponse {
	
	private String message;
	private Timestamp timestamp;
	
	public PostCommonResponse(String message, Timestamp timestamp) {
		this.message=message;
		this.timestamp=timestamp;
	}

	public String getMessage() {
		return message;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}
}
