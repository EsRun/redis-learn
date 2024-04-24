package com.redis.www;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@GetMapping("/set/{key}/{value}")
	public String setKeyValue(@PathVariable(name = "key") String key, @PathVariable(name = "value") String value) {
		redisTemplate.opsForValue().set(key,  value);
		return "ok";
	}
	
	@GetMapping("/get/{key}")
	public String getValue(@PathVariable(name = "key") String key) {
		return redisTemplate.opsForValue().get(key);
	}
	
	@GetMapping("/redis/keys")
	public Set<String> getKeys(){
		return redisTemplate.keys("*");
	}
}
