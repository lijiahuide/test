package com.redis;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ReUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClient {

	public static void main(String[] args) {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(10000);
		config.setMaxIdle(5000);
		config.setMaxWaitMillis(10000);
		config.setTestOnBorrow(false);
		JedisPool pool = new JedisPool(config, "192.168.1.106", 6379, 1000, "123456");
		final Jedis jedis = pool.getResource();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 1000000; i++) {
					jedis.sadd(i + Thread.currentThread().getName(), UUID.randomUUID().toString());
					System.out.println(i);
				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 1000000; i++) {
					jedis.sadd(i + Thread.currentThread().getName(), UUID.randomUUID().toString());
					System.out.println(i);
				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 1000000; i++) {
					jedis.sadd(i + Thread.currentThread().getName(), UUID.randomUUID().toString());
					System.out.println(i);
				}
			}
		}).start();
		System.out.println("操作完成");
		// jedis.close();
	}

}
