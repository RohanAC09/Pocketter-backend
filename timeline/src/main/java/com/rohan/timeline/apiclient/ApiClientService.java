package com.rohan.timeline.apiclient;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.rohan.timeline.dto.request.PostRequest;

@Component
public class ApiClientService {
	
	private final WebClient webClient;
	
	public ApiClientService(WebClient.Builder webClient) {
		this.webClient=webClient.build();
	}

	public <T> T callPostWithPostIds(String postBackendGetPostsById, PostRequest postRequest,
											Class<T> entityClass, String jwt) {
		return webClient.post()
					.uri(postBackendGetPostsById)
					.header("Authorization", "Bearer " + jwt)
					.bodyValue(postRequest)
					.retrieve()
					.bodyToMono(entityClass)
					.block();
	}

	public <T> T callPostWithUserId(String postBackendRecentPostsByUserId, Long userId,
											Class<T> entityClass, String jwt) {
		return webClient.get()
					.uri(postBackendRecentPostsByUserId, userId)
					.header("Authorization", "Bearer " + jwt)
					.retrieve()
					.bodyToMono(entityClass)
					.block();
	}
}
