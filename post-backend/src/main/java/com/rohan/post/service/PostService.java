package com.rohan.post.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rohan.post.apiclient.ApiClientService;
import com.rohan.post.dto.request.PostRequest;
import com.rohan.post.dto.response.PostCommonResponse;
import com.rohan.post.repository.PostRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ApiClientService apiClientService;
	
	public PostCommonResponse createPost(PostRequest postRequest, String username) {
		return new PostCommonResponse(null, null);
	}

}
