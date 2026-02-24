package com.rohan.post.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.rohan.post.apiclient.ApiClientService;
import com.rohan.post.config.dto.auth.AuthPrincipal;
import com.rohan.post.dto.request.PostRequest;
import com.rohan.post.dto.request.UserFollowers;
import com.rohan.post.dto.response.PostCommonResponse;
import com.rohan.post.entity.Post;
import com.rohan.post.kafka.KafkaEventHandler;
import com.rohan.post.repository.PostRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ApiClientService apiClientService;
	
	@Autowired
	private KafkaEventHandler kafkaEventHandler;
	
	@Value("${user.backend.fetch.followerId}")
	private String userFetchFollowerid;
	
	public PostCommonResponse createPost(PostRequest postRequest, Authentication auth) {
		
		AuthPrincipal authPrincipal = (AuthPrincipal) auth.getPrincipal();

		postRepository.saveAndFlush(
				Post.builder()
				.userId(postRequest.getUserId())
				.username(authPrincipal.getUsername())
				.content(postRequest.getContent())
				.createdAt(new Timestamp(System.currentTimeMillis()))
				.build());
		
		try {
			UserFollowers userFollowers= apiClientService.CallUserAndFetchFollowerId(userFetchFollowerid, 
											authPrincipal.getJwt(), postRequest.getUserId());
			log.info(userFollowers.toString());
			
			kafkaEventHandler.createPostEvent(userFollowers);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return new PostCommonResponse("New post created successfully", new Timestamp(System.currentTimeMillis()));
	}

}
