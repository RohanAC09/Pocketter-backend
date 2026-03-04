package com.rohan.timeline.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import com.rohan.timeline.dto.response.PostDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RedisService {
	
	@Autowired
	private RedisTemplate<String, Long> redisTemplate;
	
	@Value("${redis.cache.postids.size}")
	int redisCachePostIdsSize;

    public void addSinglePost(Long userId, Long postId, Double timeInDouble) {
        String key = getBuiltKey(userId);
        redisTemplate.opsForZSet().add(key, postId, timeInDouble);
        
        Long size = redisTemplate.opsForZSet().size(key);
        if(size != null && size>redisCachePostIdsSize) {
        	redisTemplate.opsForZSet().removeRange(key, 0, size-redisCachePostIdsSize-1); // size-201
        }
    }

    public List<Long> getLatestPosts(Long userId) {
        String key = getBuiltKey(userId);
        Set<Long> postIds = redisTemplate.opsForZSet().reverseRange(key, 0, redisCachePostIdsSize-1); // 199
        return postIds == null ? List.of() : new ArrayList<>(postIds);
    }

	private String getBuiltKey(Long userId) {
        return "timeline:user:" + userId;
    }
	
	public void addPostsBatch(Long userId, List<PostDTO> posts) {
	    String key = getBuiltKey(userId);

	    Set<ZSetOperations.TypedTuple<Long>> PostTuples = posts.stream()
	                    .map(post -> new DefaultTypedTuple<>(post.getPostId(), (double) post.getCreatedAt().getTime()))
	                    .collect(Collectors.toSet());

	    redisTemplate.opsForZSet().add(key, PostTuples);

	    Long size = redisTemplate.opsForZSet().size(key);
        if(size != null && size>redisCachePostIdsSize) {
        	redisTemplate.opsForZSet().removeRange(key, 0, size-redisCachePostIdsSize-1); // size-201
        }
	}
    
	//  public <T> T get(String key, Class<T> entityClass) {
	//		try {
	//			Object o = redisTemplate.opsForValue().get(key);
	//			ObjectMapper mapper=new ObjectMapper();
	//			return mapper.readValue(o.toString(), entityClass);
	//		} catch (JsonMappingException e) {
	//			log.error(e.getMessage());
	//		} catch (JsonProcessingException e) {
	//			log.error(e.getMessage());
	//		}
	//		return null;
	//	}
}
