package com.rohan.timeline.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.rohan.timeline.apiclient.ApiClientService;
import com.rohan.timeline.config.dto.auth.AuthPrincipal;
import com.rohan.timeline.dto.request.PostRequest;
import com.rohan.timeline.dto.response.AggregatedPosts;

@Service
public class TimelineService {
	
	@Autowired
	private RedisTemplate<String, Long> redisTemplate;
	
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
		
		// Call Redis and get PostIds
		List<Long> postIds=new ArrayList<>();
		
		if( !postIds.isEmpty() ) {
			PostRequest postRequest=new PostRequest(userId, postIds);
			aggregatedPosts = apiClientService.callPostWithPostIds(postBackendGetPostsById, postRequest, 
													AggregatedPosts.class, authPrincipal.getJwt());
		} else {
			aggregatedPosts = apiClientService.callPostWithUserId(postBackendRecentPostsByUserId, userId, 
													AggregatedPosts.class, authPrincipal.getJwt());
			
			// Store post ids to Redis cache
		}
		return aggregatedPosts;
	}

    private String getTimelineKey(Long userId) {
        return "timeline:user:" + userId;
    }

}
