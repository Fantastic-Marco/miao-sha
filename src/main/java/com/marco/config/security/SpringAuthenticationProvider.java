//package com.marco.config.security;
//
//import com.marco.service.UserService;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.context.SecurityContextImpl;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.util.Assert;
//
//
///**
// * Created by landun on 2018/7/2.
// */
//@Component
//public class SpringAuthenticationProvider implements AuthenticationProvider {
//    private Logger logger = LogManager.getRootLogger();
//
//    @Autowired
//    UserService userService;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, authentication, "非验证类型");
//        //账号
//        String username = authentication.getName();
//        //密码
//        String password = authentication.getCredentials().toString();
//        UserDetails userDetails = userService.loadUserByUsername(username);
//        if (userDetails == null) {
//            logger.debug("用户名不存在");
//            return null;
//        } else {
//            if (!userDetails.getPassword().equals(password)) {
//                logger.debug("密码错误");
//                return null;
//            }
//            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
//            token.setDetails(userDetails);
//            SecurityContextHolder.setContext(new SecurityContextImpl(token));
//            return token;
//        }
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return (UsernamePasswordAuthenticationToken.class
//                .isAssignableFrom(authentication));
//    }
//}
