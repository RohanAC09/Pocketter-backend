package com.rohan.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rohan.user.entity.Follows;

@Repository
public interface FollowRepository extends JpaRepository<Follows, Long> {

	public Optional<Follows> findByFolloweeIdAndFollowerId(Long userId, Long currUserId);

	public void deleteByFolloweeIdAndFollowerId(Long followeeUserId, Long currUserId);
	
	@Query(value="SELECT f.follower_id FROM follows f WHERE f.followee_id= :userId", nativeQuery = true)
	public List<Long> findAllFollowerId(@Param("userId") Long userId);

	@Query(value="SELECT f.followee_id FROM follows f WHERE f.follower_id= :userId", nativeQuery = true)
	public List<Long> findAllFolloweeId(@Param("userId") Long userId);

}
