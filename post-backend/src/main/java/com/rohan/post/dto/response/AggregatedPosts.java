package com.rohan.post.dto.response;

import java.util.List;

import lombok.Builder;

@Builder
public class AggregatedPosts {
	
	private Long userId;
	private List<PostDTO> posts;
	
	public Long getUserId() {
		return userId;
	}
	public List<PostDTO> getPosts() {
		return posts;
	}
}
