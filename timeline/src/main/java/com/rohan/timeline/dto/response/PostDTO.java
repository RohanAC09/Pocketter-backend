package com.rohan.timeline.dto.response;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PostDTO {
	
	private Long postId;
    private Long userId;
    private String content;
    private String username;
    private Timestamp createdAt;
}
