package com.marco.config.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by landun on 2018/6/6.
 * <p>
 * 数据源连接信息
 * 读取自application.yml
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource")
@PropertySource(value = "classpath:application.yml")
public class DatasourceConf {
    private String url;     //数据库url
    private String username;        //用户名
    private String password;        //密码
    private String driverClassName;     //驱动类名
    private String connectionProperties;        //连接属性


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getConnectionProperties() {
        return connectionProperties;
    }

    public void setConnectionProperties(String connectionProperties) {
        this.connectionProperties = connectionProperties;
    }

}
