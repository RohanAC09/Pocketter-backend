package com.rohan.timeline.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
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
