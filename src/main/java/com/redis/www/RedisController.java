package com.redis.www;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
	
	private final StringRedisTemplate redisTemplate;
	private final RedisService service;
	
	@Autowired
	public RedisController(StringRedisTemplate redisTemplate, RedisService service) {
		this.redisTemplate = redisTemplate;
		this.service = service;
	}
	
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
	public Map<String, Object> getKeys(@RequestParam(name = "key", defaultValue = "1") String key){
		Set<String> keys = redisTemplate.keys("*");
		List<String> values = redisTemplate.opsForValue().multiGet(keys);
		Map<String, Object> keyValueMap = new HashMap<>();

		// Populate the map with keys and their corresponding values
		Iterator<String> iterator = keys.iterator();
		Iterator<String> valuesIterator = values.iterator();
		while (iterator.hasNext() && valuesIterator.hasNext()) {
		    String keyy = iterator.next();
		    Object value = valuesIterator.next();
		    keyValueMap.put(keyy, value);
		}

		return keyValueMap;
	}
	
	@GetMapping("/redis/insert")
	public Set<String> insertCache(@RequestParam(name = "key") String key){		
		System.out.println("Controller = " + service.insert(key));
		return redisTemplate.keys("*");
	}
	
	@GetMapping("/redis/update")
	public Set<String> updateCache(@RequestParam(name = "key") String key){
		service.update(key);
		return redisTemplate.keys("*");
	}
	
}
