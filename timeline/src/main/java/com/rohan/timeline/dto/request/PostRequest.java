package com.rohan.timeline.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PostRequest {
	
	private Long userId;
	private List<Long> postIds;
	
}
