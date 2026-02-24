package com.rohan.post.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserFollowers {
	
	private String userId;
	private String username;
	private List<Long> followerIds;
	
	public String getUserId() {
		return userId;
	}
	public String getUsername() {
		return username;
	}
	public List<Long> getFollowerIds() {
		return followerIds;
	}

}
