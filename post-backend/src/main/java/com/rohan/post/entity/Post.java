package com.rohan.post.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="post")
public class Post {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="post_id")
    private Long postId;

    @Column(name="user_id")
	private Long userId;
    
    @Column(name="content")
    private String content;
    
    @Column(name="username")
    private String username;
    
    @Column(name="created_at")
    private Timestamp createdAt;
    
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
	public Long getPostId() {
		return postId;
	}
}
