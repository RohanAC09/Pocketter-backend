package com.rohan.post.apiclient;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ApiClientService {
	
private final WebClient webClient;
	
	public ApiClientService(WebClient.Builder webClient) {
		this.webClient=webClient.build();
	}

	public String callWithPathVariable(String userBackendCreateUser, String jwt, String email) {
		return webClient.post()
				.uri(userBackendCreateUser, email)
				.header("Authorization", "Bearer " + jwt)
				// .bodyValue(RequestBody)
				.retrieve()
				.bodyToMono(String.class)
				.block();
	}
}
