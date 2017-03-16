package com.ihbaby.sys;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;


public class CacheUtil {
	
	
	public static <T> T serializerJSON(RedisTemplate<String, Object> redisTemplate, String key, Class<T> clazz) {
		String jsonStr = (String) redisTemplate.opsForValue().get(key);
		if (StringUtils.isBlank(jsonStr)) {
			return null;
		}
		return JSONObject.parseObject(jsonStr, clazz);
	}
}
