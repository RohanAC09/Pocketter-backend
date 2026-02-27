package com.rohan.user.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rohan.user.dto.request.EditUserRequest;
import com.rohan.user.dto.response.FetchFollowee;
import com.rohan.user.dto.response.FetchFollower;
import com.rohan.user.dto.response.UserResponse;
import com.rohan.user.entity.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserProfileService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FollowService followService;

	public UserResponse createUser(String email) {
		
		if (userService.isUserExists(email)) {
        	log.info("CreateUser Error: user already exists: {}", email);
            return new UserResponse(new String("User already exists"), new Timestamp(System.currentTimeMillis()));
        }

        userService.createUser(email);
        log.info("New user {} has been created", email);
        
        String responsemessage="User creation successful";
        return new UserResponse(responsemessage, new Timestamp(System.currentTimeMillis()));
	}

	public String viewProfile(Long userId) {
		Optional<User> optionalUser=userService.findUserByUserId(userId);
		
		if ( !optionalUser.isPresent()) {
        	log.info("viewProfile Error: user does not exists: {}", userId);
            return new UserResponse(new String("User does not exists"), new Timestamp(System.currentTimeMillis())).toString();
        }
        
        return optionalUser.get().toString();
	}

	public UserResponse editProfile(Long userId, EditUserRequest userRequest, String username) {
		Optional<User> optionalUser=userService.findUserByUserId(userId);
		
		if ( !optionalUser.isPresent()) {
        	log.info("editProfile Error: user does not exists: {}", userId);
            return new UserResponse(new String("Could not perform operation. User does not exists"), 
            		new Timestamp(System.currentTimeMillis()));
        }

		User user=optionalUser.get();
		if( !username.equals(user.getUsername())) {
			return new UserResponse(new String("User action not allowed. Unauthorised!"), 
					new Timestamp(System.currentTimeMillis()));
		}
		
		user.setBio(userRequest.getBio());
		user.setFullName(userRequest.getFullName());
		
		userService.saveUser(user);
		return new UserResponse(new String("Update profile successful."), 
        		new Timestamp(System.currentTimeMillis()));
	}

	public UserResponse deleteUserByEmail(String email) {
		
		if ( !userService.isUserExists(email)) {
        	log.info("deleteUserByEmail Error: user does not exists: {}", email);
            return new UserResponse(new String("User does not exists."), new Timestamp(System.currentTimeMillis()));
        }
		
		userService.deleteUserByEmail(email);
        String responsemessage="Deletion operation successful. Deleted User: " + email;
        log.info(responsemessage);
        
        return new UserResponse(responsemessage, new Timestamp(System.currentTimeMillis()));
	}

	public UserResponse followUser(Long followeeUserId, String followerUsername) {
		
		if( !userService.findUserByUserId(followeeUserId).isPresent()) {
			log.info("followUser Error: user does not exists: {}", followerUsername);
            return new UserResponse(new String("User does not exists."), new Timestamp(System.currentTimeMillis()));
		}
		
		Long currUserId = userService.findUserIdByUsername(followerUsername);
		
		if(currUserId == followeeUserId) {
			return new UserResponse(new String("Please search other users."), new Timestamp(System.currentTimeMillis()));
		}
		
		if( followService.isUserAlreadyFollowing(followeeUserId, currUserId)) {
			log.info("followUser Info: already following user: {}", followeeUserId);
            return new UserResponse(new String("Already following the user."), new Timestamp(System.currentTimeMillis()));
		}
		
		followService.followUser(followeeUserId, currUserId);
		
		return new UserResponse(new String("You are following the user."), new Timestamp(System.currentTimeMillis()));
	}

	public UserResponse unfollowUser(Long followeeUserId, String followerUsername) {
		
		if( !userService.findUserByUserId(followeeUserId).isPresent()) {
			log.info("followUser Error: user does not exists: {}", followerUsername);
            return new UserResponse(new String("User does not exists."), new Timestamp(System.currentTimeMillis()));
		}
		
		Long currUserId = userService.findUserIdByUsername(followerUsername);
		
		if(currUserId == followeeUserId) {
			return new UserResponse(new String("Invalid operation."), new Timestamp(System.currentTimeMillis()));
		}
		
		if( !followService.isUserAlreadyFollowing(followeeUserId, currUserId)) {
			log.info("followUser Info: you are not following user: {}", followeeUserId);
            return new UserResponse(new String("You are not following the user."), new Timestamp(System.currentTimeMillis()));
		}
		
		followService.unfollowUser(followeeUserId, currUserId);
		
		return new UserResponse(new String("You are unfollowing the user."), new Timestamp(System.currentTimeMillis()));
	}

	public FetchFollower fetchFollowerId(Long followeeId, String username) {
		
		List<Long> followerIds=followService.fetchAllFollowerIds(followeeId);
		
		return FetchFollower.builder()
				.userId(followeeId)
				.username(username)
				.followerIds(followerIds)
				.build();
	}
	
	public FetchFollowee fetchFolloweeId(Long followerId, String username) {
		
		List<Long> followeeIds=followService.fetchAllFolloweeIds(followerId);
		
		return FetchFollowee.builder()
				.userId(followerId)
				.username(username)
				.followeeIds(followeeIds)
				.build();
	}

}
