package com.rohan.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rohan.user.dto.request.EditUserRequest;
import com.rohan.user.dto.response.UserResponse;
import com.rohan.user.entity.User;
import com.rohan.user.service.UserProfileService;

@RestController
@RequestMapping("/api/v1/profile")
public class UserProfileController {
	
	@Autowired
	UserProfileService userProfileService;
	
	@PostMapping("/createUser/{email}")
	public ResponseEntity<UserResponse> createUser(@PathVariable String email) {
		return ResponseEntity.ok(userProfileService.createuser(email));
	}
	
	@GetMapping("/viewProfile/{userId}")
	public ResponseEntity<User> viewProfile(@PathVariable String userId) {
		return ResponseEntity.ok(new User());
	}
	
	@PutMapping("/editProfile/{userId}")
	public ResponseEntity<UserResponse> editProfile(@PathVariable String userId, @RequestBody EditUserRequest userRequest) {
		return ResponseEntity.ok(new UserResponse(null, null));
	}
	
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<UserResponse> deleteUser(@PathVariable String userId) {
		return ResponseEntity.ok(new UserResponse(null, null));
	}
	

}
