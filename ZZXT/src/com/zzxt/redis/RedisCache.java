package com.zzxt.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zzxt.bean.TreeBean;

import redis.clients.jedis.JedisCluster;

/**
 * 
 * @ClassName: RedisCache
 * @Description: redis 缓存
 * @author wangdekun
 * @date 2017年8月29日 上午11:47:34
 *
 */
//@Component
public class RedisCache {

//	@Autowired
//	private JedisCluster jedisCluster;

//	private static Logger logger = Logger.getLogger(RedisCache.class.getName());

//	public String set(String key, String value) {
//		return jedisCluster.set(key, value);
//	}
//
//	public String set(String key, String value, int time) {
//		return jedisCluster.setex(key, time, value);
//	}
//
//	public String get(String key) {
//		return jedisCluster.get(key);
//	}
//
//	public String hmset(String key, Map<String, String> hash) {
//		return jedisCluster.hmset(key, hash);
//
//	}
//	public String hmget(String key, String keyMap) {
//		return jedisCluster.hget(key, keyMap);
//	}

}
