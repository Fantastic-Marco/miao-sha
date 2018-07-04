package com.marco.security;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

/**
 * Created by landun on 2018/5/25.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static int TOKEN_TIMEOUT = 10;     //30秒

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()//访问：/登录接口 无需登录认证权限
                .antMatchers("/static/**").permitAll()   //静态资源无需认证权限
                .antMatchers("/hello").hasRole("USER") //登陆后之后拥有“ADMIN”权限才可以访问/hello方法，否则系统会出现“403”权限不足的提示
                .antMatchers("/admin/*").hasRole("ADMIN") //登陆后之后拥有“DBA”权限才可以访问/admin接口方法，否则系统会出现“403”权限不足的提示
                .anyRequest().authenticated() //其他所有资源都需要认证，登陆后访问
                .and()
                .formLogin()
                .loginPage("/to_login")//指定登录页是”/to_login”
                .permitAll()
                .and()
                .logout()
//                .logoutSuccessUrl("/to_login") //退出登录后的默认网址是”/to_login”
                .permitAll()
                .invalidateHttpSession(true)
                .and()
                .rememberMe()//登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
                .tokenValiditySeconds(TOKEN_TIMEOUT);
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }
}
