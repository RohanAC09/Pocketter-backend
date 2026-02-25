package com.rohan.post.service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.rohan.post.apiclient.ApiClientService;
import com.rohan.post.config.dto.auth.AuthPrincipal;
import com.rohan.post.dto.request.CreatePostRequest;
import com.rohan.post.dto.request.PostIdRequest;
import com.rohan.post.dto.request.UserFollowers;
import com.rohan.post.dto.response.AggregatedPosts;
import com.rohan.post.dto.response.PostCommonResponse;
import com.rohan.post.dto.response.PostDTO;
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
	
	public PostCommonResponse createPost(CreatePostRequest postRequest, Authentication auth) {
		
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

	public AggregatedPosts getPostsByIds(PostIdRequest postIdRequest) {
		
		List<Post> posts=postRepository.findAllById(postIdRequest.getPostIds());
		
		List<PostDTO> postsDTO = ( posts == null ? List.of() : 
									posts.stream().map((post) -> new PostDTO(post)).toList() );
		return AggregatedPosts.builder()
				.userId(postIdRequest.getUserId())
				.posts(postsDTO)
				.build();
	}

	public AggregatedPosts getPostsForUser(Long userId) {
		
		return null;
	}

}
