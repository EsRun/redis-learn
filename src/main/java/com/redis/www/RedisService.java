package com.redis.www;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
	
	@Cacheable(cacheNames = "test", key = "'#keys'", cacheManager = "cacheManager")
	public String insert(String keys) {
		return keys;
	}
	
	@CachePut(cacheNames = "test", key = "'#keys'", cacheManager = "cacheManager")
	public void update(String keys) {
	}
	
	@CacheEvict(cacheNames = "test", key = "'#keys'", cacheManager = "cacheManager")
	public void delete(String keys) {
	}
	
}
