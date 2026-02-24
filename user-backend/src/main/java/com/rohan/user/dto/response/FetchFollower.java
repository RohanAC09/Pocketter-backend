package com.rohan.user.dto.response;

import java.util.List;

import lombok.Builder;

@Builder
public class FetchFollower {
	
	private Long userId;
	private String username;
	private List<Long> followerIds;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<Long> getFollowerids() {
		return followerIds;
	}
	public void setFollowerids(List<Long> followerids) {
		this.followerIds = followerids;
	}
}
