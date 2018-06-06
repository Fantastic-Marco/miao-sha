package com.marco.controller;

import com.marco.result.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by landun on 2018/5/23.
 */
@Controller
public class DemoController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
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
