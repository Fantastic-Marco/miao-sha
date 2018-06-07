package com.marco.config.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by landun on 2018/6/6.
 */
@Component
@ConfigurationProperties(prefix = "redis")
@PropertySource(value = "classpath:redis.properties")
public class RedisConf {
    private String host;
    private int port;
    private String password;
    private int database;
    private int timeout;
    private int maxIdel;
    private int minIdel;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getMaxIdel() {
        return maxIdel;
    }

    public void setMaxIdel(int maxIdel) {
        this.maxIdel = maxIdel;
    }

    public int getMinIdel() {
        return minIdel;
    }

    public void setMinIdel(int minIdel) {
        this.minIdel = minIdel;
    }
}
