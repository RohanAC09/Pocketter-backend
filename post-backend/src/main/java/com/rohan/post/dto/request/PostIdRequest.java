package com.rohan.post.dto.request;

import java.util.List;

public class PostIdRequest {
	
	private Long userId;
	private List<Long> postIds;

	public List<Long> getPostIds() {
		return postIds;
	}
	public Long getUserId() {
		return userId;
	}
}
