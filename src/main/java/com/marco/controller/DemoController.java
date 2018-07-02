package com.marco.controller;

import com.marco.config.conf.RedisConf;
import com.marco.config.redis.key.AdminPrefix;
import com.marco.ov.AdminOV;
import com.marco.result.Msg;
import com.marco.security.LoginSuccessHandler;
import com.marco.security.SpringAuthenticationProvider;
import com.marco.service.RedisService;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * 这是一个专门用来测试用的Controller
 * Created by landun on 2018/5/23.
 */
@Controller
public class DemoController {
    @Autowired
    RedisService redisService;
    @Autowired
    SpringAuthenticationProvider authenticationProvider;
    @Autowired
    LoginSuccessHandler handler;

    @RequestMapping("/")
    @ResponseBody
    Msg<RedisConf> home() {
        redisService.setKey(new AdminPrefix(), "test", 2);
        float value = redisService.getKey(new AdminPrefix(), "test", Float.class);
        return Msg.returnSuccess(value);
    }

    @RequestMapping("/hello")
    @ResponseBody
    Msg<String> hello() {
        return Msg.returnSuccess("hello");
    }

    @RequestMapping("/to_login")
    String toLogin(Model model) {
        model.addAttribute("_csrf.token", UUID.randomUUID().toString());
        return "login";
    }

    @RequestMapping("/test")
    String test(Model model) {
        model.addAttribute("test", "hello thymeleaf");
        return "index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    String login(HttpServletRequest request, HttpServletResponse response, Model model, AdminOV adminOV) throws IOException, ServletException {
        System.out.println(adminOV);
        Authentication authentication = new UsernamePasswordAuthenticationToken(adminOV.getName(),adminOV.getPassword());
        Authentication token = authenticationProvider.authenticate(authentication);
        model.addAttribute("test", "logined");
//        handler.onAuthenticationSuccess(request,response,token);
        return "index";
    }

}
