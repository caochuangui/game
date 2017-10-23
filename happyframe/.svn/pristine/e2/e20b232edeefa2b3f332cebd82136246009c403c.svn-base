package com.frame.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author liuzhengyi
 * @date 2014年12月4日 下午3:54:37
 */
public class JedisUtil {
	private static JedisPool pool = null;

	static {
		// JedisPoolConfig config = new JedisPoolConfig();
		// pool = new JedisPool(config, ConfigGlobal.getInstance()
		// .getSystemConfig().getRedisIP(), ConfigGlobal.getInstance()
		// .getSystemConfig().getRedisPort(), 5000);
	}

	public static void shutdown() {
		if (pool != null) {
			pool.destroy();
		}
	}

	public static Jedis getJedis() {
		return pool.getResource();
	}

	public static void return2Pool(Jedis jedis) {
		if (jedis != null) {
			pool.returnResource(jedis);
		}
	}

	public static void returnBrokenResource(Jedis jedis) {
		pool.returnBrokenResource(jedis);
	}
}
