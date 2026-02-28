package com.rohan.post.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rohan.post.dto.request.CreatePostRequest;
import com.rohan.post.dto.request.PostIdRequest;
import com.rohan.post.dto.response.AggregatedPosts;
import com.rohan.post.dto.response.PostCommonResponse;
import com.rohan.post.service.PostService;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@PostMapping("/createPost")
	public ResponseEntity<PostCommonResponse> createPost(@RequestBody CreatePostRequest postRequest, Authentication auth){
		return ResponseEntity.ok(postService.createPost(postRequest, auth));
	}
	
	@PostMapping("/getPostsByIds")
	public ResponseEntity<AggregatedPosts> getPostsById(@RequestBody PostIdRequest postIdRequest){
		return ResponseEntity.ok(postService.getPostsByIds(postIdRequest));
	}
	
	@GetMapping("/getPostsForUser/{userId}")
	public ResponseEntity<AggregatedPosts> getPostsForUser(@PathVariable Long userId){
		return ResponseEntity.ok(postService.getPostsForUser(userId));
	}
}
