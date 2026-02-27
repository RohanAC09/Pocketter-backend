package com.rohan.post.dto.response;

import java.sql.Timestamp;

import com.rohan.post.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class PostDTO {
	
	private Long postId;
    private Long userId;
    private String content;
    private String username;
    private Timestamp createdAt;
    
    public PostDTO(Post post) {
    	this.postId=post.getPostId();
    	this.userId=post.getUserId();
    	this.content=post.getContent();
    	this.username=post.getUsername();
    	this.createdAt=post.getCreatedAt();
    }
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}
