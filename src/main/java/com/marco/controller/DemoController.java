package com.marco.controller;

import com.marco.config.conf.RedisConf;
import com.marco.config.redis.key.AdminPrefix;
import com.marco.result.Msg;
import com.marco.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

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
        float value = redisService.getKey(new AdminPrefix(),"test",Float.class);
        return Msg.returnSuccess(value);
    }

    @RequestMapping("/hello")
    @ResponseBody
    Msg<String> hello() {
        return Msg.returnSuccess("hello");
    }

    @RequestMapping("/hi")
    @ResponseBody
    Msg<String> hi() {
        return Msg.ORDER_NO_LEVEL;
    }

}
