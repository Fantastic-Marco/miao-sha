package com.marco.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.marco.config.conf.DatasourceConf;
import com.marco.config.conf.DruidConf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by landun on 2018/6/6.
 */
@Configuration
public class DruidConfiguration {
    private Logger logger = LogManager.getLogger(DruidConfiguration.class);

    @Autowired
    DatasourceConf datasourceConf;
    @Autowired
    DruidConf druidConf;

    /**
     * Druid 认证配置
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        registrationBean.addInitParameter("allow", "127.0.0.1");
        registrationBean.addInitParameter("deny", "192.168.31.234");
        registrationBean.addInitParameter("loginUsername", "admin");
        registrationBean.addInitParameter("loginPassword", "marco520");
        registrationBean.addInitParameter("resetEnable", "false");
        return registrationBean;
    }

    /**
     * Druid 拦截器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean druidWebStatViewFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new WebStatFilter());
        registrationBean.addInitParameter("urlPatterns", "/*");
        registrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        return registrationBean;

    }

    /**
     * Druid数据源初始化
     *
     * @return
     */
    @Bean
    public DataSource druidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        /*数据源主要配置*/
        dataSource.setUrl(datasourceConf.getUrl());
        dataSource.setDriverClassName(datasourceConf.getDriverClassName());
        dataSource.setUsername(datasourceConf.getUsername());
        dataSource.setPassword(datasourceConf.getPassword());
        /*数据源补充配置*/
        dataSource.setMaxActive(druidConf.getMaxActive());
        dataSource.setInitialSize(druidConf.getInitialSize());
        dataSource.setMinIdle(druidConf.getMinIdle());
        dataSource.setMaxWait(druidConf.getMaxWait());
        dataSource.setTimeBetweenEvictionRunsMillis(druidConf.getTimeBetweenEvictionRunsMillis());
        dataSource.setMinEvictableIdleTimeMillis(druidConf.getMinEvictableIdleTimeMillis());
        dataSource.setValidationQuery(druidConf.getValidationQuery());
        dataSource.setTestOnBorrow(druidConf.isTestOnBorrow());
        dataSource.setTestOnReturn(druidConf.isTestOnReturn());
        dataSource.setTestWhileIdle(druidConf.isTestWhileIdle());
        dataSource.setPoolPreparedStatements(druidConf.isPoolPreparedStatements());
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(druidConf.getMaxPoolPreparedStatementPerConnectionSize());
        dataSource.setConnectionProperties(datasourceConf.getConnectionProperties());
        dataSource.setUseGlobalDataSourceStat(druidConf.isUseGlobalDataSourceStat());
        try {
            dataSource.setFilters(druidConf.getFilters());
            logger.debug("Druid数据源初始化设置成功......");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.debug("Druid数据源filters设置失败......");
        }
        return dataSource;

    }
}
