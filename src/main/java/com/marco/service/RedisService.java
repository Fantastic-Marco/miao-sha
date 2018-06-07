package com.marco.service;

import com.alibaba.fastjson.JSON;
import com.marco.config.redis.RedisFactory;
import com.marco.config.redis.key.Prefix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * Created by landun on 2018/6/6.
 */
@Service
public class RedisService {
    private Logger logger = LogManager.getRootLogger();

    @Autowired
    RedisFactory redisFactory;

    /**
     * 往Redis里设置key
     * 引用Prefix降低key重复的概率
     *
     * @param prefix key前缀（包含key的前缀名和存活时长）
     * @param key
     * @param value
     */
    public void setKey(Prefix prefix, String key, Object value) {
        Jedis jedis = null;
        try {
            jedis = redisFactory.getJedis();
            String realKey = getRealKey(prefix, key);      //拼接前缀
            String realValue = getValue(value);
            logger.debug("redis set key {},value {}", realKey, value);
            //存活时间若小于等于0 说明该key的没有存活时间限制
            if (prefix.getExpire() > 0) {
                jedis.setex(realKey, prefix.getExpire(), realValue);
            } else {
                jedis.set(realKey, realValue);
            }
        } catch (Throwable throwable) {
            logger.error(throwable.getMessage(), throwable);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 从Redis获取值
     *
     * @param prefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getKey(Prefix prefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = redisFactory.getJedis();
            String realKey = getRealKey(prefix, key);
            String value = jedis.get(realKey);
            logger.debug("redis get key {},value {}", realKey, value);
            return (T) stringToValue(value, clazz);     //取值后进行类型转换
        } catch (Throwable throwable) {
            logger.error(throwable.getMessage(), throwable);
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 获取拼接后的key
     *
     * @param prefix
     * @param key
     * @return
     */
    private String getRealKey(Prefix prefix, String key) {
        return new StringBuilder(prefix.getPrefix()).append(":").append(key).toString();
    }

    /**
     * 获取String 类型的value
     *
     * @param value
     * @return
     */
    private String getValue(Object value) {
        if (value instanceof String) {
            return value + "";
        } else if (value instanceof Integer || value instanceof Long || value instanceof Float || value instanceof Double || value instanceof Boolean) {
            return String.valueOf(value);
        } else {
            return JSON.toJSONString(value);
        }
    }

    /**
     * 将Redis获取的值转换成指定的目标类型
     *
     * @param value Redis获取的String 值
     * @param clazz 转换目标类型
     * @return
     */
    private Object stringToValue(String value, Class clazz) {
        if (clazz == String.class) {
            return value;
        } else if (clazz == Integer.class) {
            return Integer.parseInt(value);
        } else if (clazz == Long.class) {
            return Long.parseLong(value);
        } else if (clazz == Float.class) {
            return Float.parseFloat(value);
        } else if (clazz == Double.class) {
            return Double.parseDouble(value);
        } else if (clazz == Boolean.class) {
            return Boolean.parseBoolean(value);
        } else {
            return JSON.parseObject(value, clazz);
        }
    }
}
