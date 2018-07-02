package com.marco.security;

import com.marco.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by landun on 2018/7/2.
 */
@Component
public class SpringAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    AdminService adminService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, authentication, "非验证类型");
        //账号
        String username = authentication.getName();
        //密码
        String password = authentication.getCredentials().toString();
        UserDetails userDetails = adminService.loadUserByUsername(username);
        if (userDetails == null) {
            System.out.println("用户名/密码无效");
            throw new UsernameNotFoundException("用户名/密码无效");
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
        token.setDetails(userDetails);
        SecurityContextHolder.setContext(new SecurityContextImpl(token));
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class
                .isAssignableFrom(authentication));
    }
}
