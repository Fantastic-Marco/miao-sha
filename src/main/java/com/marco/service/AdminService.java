package com.marco.service;

import com.marco.dao.AdminMapper;
import com.marco.dao.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by landun on 2018/5/23.
 */
@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public List<Admin> getAll() {
        return adminMapper.getAll();
    }

}
