package com.marco.service;

import com.marco.dao.RoleMapper;
import com.marco.dao.UserMapper;
import com.marco.dao.entity.Role;
import com.marco.dao.entity.SysUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by landun on 2018/7/4.
 */
@Service
public class UserService implements UserDetailsService {
    private Logger logger = LogManager.getRootLogger();

    @Autowired
    private UserMapper userDao;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userDao.getUserById(username);
        if (user != null) {
            List<Role> userRoles = roleMapper.getRolesByUserId(username);
            if (userRoles == null) {
                logger.debug("该用户没有设定角色");
                return null;
            }
            List<GrantedAuthority> roles = new ArrayList<>();
            userRoles.forEach(r -> {
                roles.add(new SimpleGrantedAuthority(r.getRolename()));
            });
            UserDetails userDetails = new User(username, user.getPassword(), roles);
            return userDetails;
        } else {
            return null;
        }
    }

}
