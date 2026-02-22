package com.rohan.authentication_service.apiclient;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
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
