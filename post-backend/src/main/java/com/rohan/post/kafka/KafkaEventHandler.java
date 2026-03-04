package com.rohan.post.kafka;

import org.springframework.stereotype.Component;

import com.rohan.post.dto.response.UserFollowers;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaEventHandler {

	public void createPostEvent(UserFollowers userFollowers) {
		log.info("Kafka event for: {}", userFollowers);
	}

}
