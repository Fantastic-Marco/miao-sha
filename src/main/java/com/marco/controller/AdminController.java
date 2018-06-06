package com.marco.controller;

import com.marco.dao.entity.Admin;
import com.marco.result.Msg;
import com.marco.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by landun on 2018/5/23.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/list")
    @ResponseBody
    public Msg<Admin> getAll() {
        List<Admin> admins = adminService.getAll();
        System.out.println(admins);
        return Msg.returnSuccess(admins);
    }
}
