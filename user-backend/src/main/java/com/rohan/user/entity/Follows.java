package com.rohan.user.entity;

import java.sql.Timestamp;

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
@Table(name="follows")
public class Follows {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;

    private Long followeeId;
    private Long followerId;
    private Timestamp createdAt;
    
	public Long getFollowId() {
		return followId;
	}
	public Long getFollowerId() {
		return followerId;
	}
	public Long getFolloweeId() {
		return followeeId;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
}
