package com.marco.controller;

import com.marco.config.conf.RedisConf;
import com.marco.config.redis.key.AdminPrefix;
import com.marco.ov.AdminOV;
import com.marco.result.Msg;
import com.marco.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping("/login")
    String login(Model model, AdminOV adminOV){
        System.out.println(adminOV);
        model.addAttribute("test", "logined");
        return "index";
    }

}
