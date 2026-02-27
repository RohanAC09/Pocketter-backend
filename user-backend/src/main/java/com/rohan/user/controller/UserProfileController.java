package com.rohan.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rohan.user.dto.request.EditUserRequest;
import com.rohan.user.dto.response.FetchFollowee;
import com.rohan.user.dto.response.FetchFollower;
import com.rohan.user.dto.response.UserResponse;
import com.rohan.user.service.UserProfileService;

@RestController
@RequestMapping("/api/v1/profile")
public class UserProfileController {
	
	@Autowired
	private UserProfileService userProfileService;
	
	@PostMapping("/createUser/{email}")
	public ResponseEntity<UserResponse> createUser(@PathVariable String email) {
		return ResponseEntity.ok(userProfileService.createUser(email));
	}
	
	@GetMapping("/viewProfile/{userId}")
	public ResponseEntity<String> viewProfile(@PathVariable Long userId) {
		return ResponseEntity.ok(userProfileService.viewProfile(userId));
	}
	
	@PutMapping("/editProfile/{userId}")
	public ResponseEntity<UserResponse> editProfile(@PathVariable Long userId, @RequestBody EditUserRequest userRequest,
													Authentication auth) {
		return ResponseEntity.ok(userProfileService.editProfile(userId, userRequest, auth.getName()));
	}
	
	@PostMapping("/follow/{userId}")
	public ResponseEntity<UserResponse> followUser(@PathVariable Long userId,  Authentication auth) {
		return ResponseEntity.ok(userProfileService.followUser(userId, auth.getName()));
	}
	
	@DeleteMapping("/unfollow/{userId}")
	public ResponseEntity<UserResponse> unfollowUser(@PathVariable Long userId, Authentication auth) {
		return ResponseEntity.ok(userProfileService.unfollowUser(userId, auth.getName()));
	}
	
	@DeleteMapping("/deleteUser/{email}")
	public ResponseEntity<UserResponse> deleteUser(@PathVariable String email) {
		return ResponseEntity.ok(userProfileService.deleteUserByEmail(email));
	}
	
	@GetMapping("/fetchFollowerId/{followeeId}")
	public ResponseEntity<FetchFollower> fetchFollowerId(@PathVariable Long followeeId, Authentication auth){
		return ResponseEntity.ok(userProfileService.fetchFollowerId(followeeId, auth.getName()));
	}
	
	@GetMapping("/fetchFolloweeId/{followerId}")
	public ResponseEntity<FetchFollowee> fetchFolloweeId(@PathVariable Long followerId, Authentication auth){
		return ResponseEntity.ok(userProfileService.fetchFolloweeId(followerId, auth.getName()));
	}
	

}
