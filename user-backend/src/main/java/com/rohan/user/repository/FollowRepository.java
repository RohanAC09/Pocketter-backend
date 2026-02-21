package com.rohan.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rohan.user.entity.Follows;

@Repository
public interface FollowRepository extends JpaRepository<Follows, Long> {

	public Optional<Follows> findByFolloweeIdAndFollowerId(Long userId, Long currUserId);

	public void deleteByFolloweeIdAndFollowerId(Long followeeUserId, Long currUserId);

}
