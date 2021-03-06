package com.marco.controller;

import com.marco.config.conf.RedisConf;
import com.marco.config.redis.key.AdminPrefix;
import com.marco.ov.AdminOV;
import com.marco.result.Msg;
//import com.marco.config.security.LoginSuccessHandler;
//import com.marco.config.security.SpringAuthenticationProvider;
import com.marco.service.RedisService;
import com.marco.util.ImageCodeUtil;
import com.marco.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * 这是一个专门用来测试用的Controller
 * Created by landun on 2018/5/23.
 */
@Controller
public class DemoController {
    private Logger logger = LogManager.getRootLogger();
    @Autowired
    RedisService redisService;
//    @Autowired
//    SpringAuthenticationProvider authenticationProvider;
//    @Autowired
//    LoginSuccessHandler handler;

    @GetMapping("/")
    @ResponseBody
    Msg<RedisConf> home() {
        redisService.setKey(new AdminPrefix(), "test", 2);
        float value = redisService.getKey(new AdminPrefix(), "test", Float.class);
        return Msg.returnSuccess(value);
    }

    @GetMapping("/hello")
    @ResponseBody
    Msg<String> hello() {
        return Msg.returnSuccess("hello");
    }

    @GetMapping("/to_login")
    String toLogin(Model model) {
        logger.info("to login page");
        model.addAttribute("_csrf.token", UUID.randomUUID().toString());
        return "login";
    }

    @GetMapping("/test")
    String test(Model model) {
        model.addAttribute("test", "hello thymeleaf");
        return "index";
    }

    @GetMapping(value = "/redirect")
    String redirect(){
        return "redirect:/to_login";
    }

//    @PostMapping(value = "/login")
//    @ResponseBody
//    Msg<String> login(HttpServletRequest request, AdminOV adminOV){
//        logger.debug(adminOV);
//        String sessionImgCode = (String) request.getSession().getAttribute("sessionImgCode");
//        logger.debug("sessionImgCode:" + sessionImgCode);
//        //验证码判断
//        if (!sessionImgCode.equals(adminOV.getImgcode())){
//            return Msg.LOGIN_IMGCODE_ERROR;
//        }
//        Authentication authentication = new UsernamePasswordAuthenticationToken(adminOV.getName(), adminOV.getPassword());
//        Authentication token = authenticationProvider.authenticate(authentication);
//        if (token == null) {
//            return Msg.LOGIN_USER_NOT_EXIST;
//        } else {
//            return Msg.returnSuccess("登录成功");
//        }
//    }

    @RequestMapping(value="/img/imgcode",method = RequestMethod.GET)
    String imgCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OutputStream os = response.getOutputStream();
        String ramdomCode = StringUtil.getRamdomImgCode(6);
        Map<String,Object> map = ImageCodeUtil.getImageCode(100, 36, ramdomCode);
        String simpleCaptcha = "simpleCaptcha";
        request.getSession().setAttribute(simpleCaptcha, map.get("strEnsure").toString().toLowerCase());
        request.getSession().setAttribute("codeTime",new Date().getTime());
        request.getSession().setAttribute("sessionImgCode",ramdomCode);
        try {
            ImageIO.write((BufferedImage) map.get("image"), "JPEG", os);
        } catch (IOException e) {
            return "";
        }
        return null;
    }

}
