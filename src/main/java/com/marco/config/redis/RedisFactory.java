package com.marco.config.redis;

import com.marco.config.conf.RedisConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by landun on 2018/6/6.
 * Redis 连接池，用于供应Jedis实例
 */
@Component
public class RedisFactory {
    @Autowired
    RedisConf redisConf;

    @Autowired
    JedisPool jedisPool;

    /**
     * 获取Jedis
     * @return
     */
    public Jedis getJedis() {
        Jedis jedis = jedisPool.getResource();
        if (redisConf.getPassword() == null) {
            jedis.select(redisConf.getDatabase());
        }
        return jedis;
    }


    /**
     * Redis连接池实例
     *
     * @return
     */
    @Bean(name = "jedisPool")
    public JedisPool getJedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(redisConf.getMaxIdel());
        config.setMinIdle(redisConf.getMinIdel());
        JedisPool pool = null;
        if (!StringUtils.isEmpty(redisConf.getPassword())) {
            pool = new JedisPool(config, redisConf.getHost(), redisConf.getPort(), redisConf.getTimeout() * 1000, redisConf.getPassword(), redisConf.getDatabase());
        } else {
            pool = new JedisPool(config, redisConf.getHost(), redisConf.getPort(), redisConf.getTimeout() * 1000);
        }
        return pool;
    }

}
