package com.rohan.post.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserFollowers {
	
	private String userId;
	private List<Long> followerIds;
	
	public String getUserId() {
		return userId;
	}
	public List<Long> getFollowerIds() {
		return followerIds;
	}

}
