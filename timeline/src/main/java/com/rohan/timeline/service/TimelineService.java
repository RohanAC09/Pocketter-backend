package com.rohan.timeline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.rohan.timeline.apiclient.ApiClientService;
import com.rohan.timeline.config.dto.auth.AuthPrincipal;
import com.rohan.timeline.dto.request.PostRequest;
import com.rohan.timeline.dto.response.AggregatedPosts;
import com.rohan.timeline.redis.RedisService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TimelineService {
	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private ApiClientService apiClientService;
	
	@Value("${post.backend.fetch.postbypostid}")
	private String postBackendGetPostsById;
	
	@Value("${post.backend.fetch.postbyuserid}")
	private String postBackendRecentPostsByUserId;

	public AggregatedPosts getTimeline(Long userId) {
		
		AuthPrincipal authPrincipal = (AuthPrincipal) SecurityContextHolder.getContext()
											.getAuthentication().getPrincipal();
		AggregatedPosts aggregatedPosts;
		
		List<Long> postIds= redisService.getLatestPosts(userId);
		log.info("Response from redis server: {}", postIds);
		
		if( !postIds.isEmpty() ) {
			PostRequest postRequest=new PostRequest(userId, postIds);
			aggregatedPosts = apiClientService.callPostWithPostIds(postBackendGetPostsById, postRequest, 
													AggregatedPosts.class, authPrincipal.getJwt());
			log.info("Response from post server with post ids: {}", aggregatedPosts);
		} else {
			aggregatedPosts = apiClientService.callPostWithUserId(postBackendRecentPostsByUserId, userId, 
													AggregatedPosts.class, authPrincipal.getJwt());
			log.info("Response from post server with post ids: {}", aggregatedPosts);
			if( aggregatedPosts != null && !(aggregatedPosts.getPosts().isEmpty()) ) {
				redisService.addPostsBatch(userId, aggregatedPosts.getPosts());
				log.info("Saved response from post server in Redis cache for key: timeline:user:{}", userId);
			}
		}
		return aggregatedPosts;
	}

}
