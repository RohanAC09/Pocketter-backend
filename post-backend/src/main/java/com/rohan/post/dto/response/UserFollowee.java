package com.rohan.post.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserFollowee {
	
	private String userId;
	private List<Long> followeeIds;
	
	public String getUserId() {
		return userId;
	}
	public List<Long> getFolloweeIds() {
		return followeeIds;
	}
	@Override
	public String toString() {
		return "UserFollowee [userId=" + userId + ", followeeIds=" + followeeIds + "]";
	}

}
