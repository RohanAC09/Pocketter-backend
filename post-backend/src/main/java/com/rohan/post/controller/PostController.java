package com.rohan.post.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rohan.post.dto.request.PostRequest;
import com.rohan.post.dto.response.PostCommonResponse;
import com.rohan.post.service.PostService;

@RestController
@RequestMapping("/api/v1/profile")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@PostMapping("/createPost")
	public ResponseEntity<PostCommonResponse> createPost(@RequestBody PostRequest postRequest, Authentication auth){
		return ResponseEntity.ok(new PostCommonResponse(null, null));
	}

}
