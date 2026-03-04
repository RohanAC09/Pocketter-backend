package com.rohan.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rohan.post.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	
	@Query(value = """
		    SELECT * FROM (
		        SELECT p.*,
		               ROW_NUMBER() OVER (
		                   PARTITION BY p.user_id
		                   ORDER BY p.created_at DESC
		               ) as rn
		        FROM post p
		        WHERE p.user_id IN (:userIds)
		    ) ranked
		    WHERE ranked.rn <= :recordsPerUser
		""", nativeQuery = true)
	List<Post> findRecentPostByUserId(@Param("userIds") List<Long> userIds, 
						@Param("recordsPerUser") Long recordsPerUser);
	
	@Query(value = "SELECT * FROM post p WHERE p.post_id IN (:postIds) "
						+ "ORDER BY p.created_at DESC", nativeQuery = true)
	List<Post> findAllPostsByPostIds(@Param("postIds") List<Long> postIds);

}
