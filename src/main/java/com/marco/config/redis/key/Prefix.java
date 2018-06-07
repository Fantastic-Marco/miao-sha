package com.marco.config.redis.key;

/**
 * Created by landun on 2018/6/6.
 */
public interface Prefix {

    int getExpire();

    String getPrefix();

}
