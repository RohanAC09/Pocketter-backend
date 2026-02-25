package com.rohan.user.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rohan.user.entity.Follows;
import com.rohan.user.repository.FollowRepository;

@Service
public class FollowService {
	
	@Autowired
	private FollowRepository followRepository;

	public boolean isUserAlreadyFollowing(Long userId, Long currUserId) {
		return followRepository.findByFolloweeIdAndFollowerId(userId, currUserId).isPresent();
	}

	public void followUser(Long userId, Long currUserId) {
		followRepository.saveAndFlush(
                Follows.builder()
                .followeeId(userId)
                .followerId(currUserId)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build() );
	}

	public void unfollowUser(Long followeeUserId, Long currUserId) {
		followRepository.deleteByFolloweeIdAndFollowerId(followeeUserId, currUserId);
	}

	public List<Long> fetchAllFollowerIds(Long userId) {
		return followRepository.findAllFollowerId(userId);
	}

	public List<Long> fetchAllFolloweeIds(Long userId) {
		return followRepository.findAllFolloweeId(userId);
	}
	
	
}
