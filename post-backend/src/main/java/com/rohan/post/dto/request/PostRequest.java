package com.rohan.post.dto.request;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PostRequest {
	
	private Long userId;
	private String content;
	
	public Long getUserId() {
		return userId;
	}
	public String getContent() {
		return content;
	}
}
