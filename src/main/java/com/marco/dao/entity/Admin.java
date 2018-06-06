package com.marco.dao.entity;

/**
 * Created by landun on 2018/5/23.
 */


/**
 * 管理员
 */
public class Admin {
    private Integer id;     //唯一标识符
    private String name;    //用户名、登录名
    private String password;    //密码
    private String token;       //token

    public Admin() {
    }

    public Admin(Integer id, String name, String password, String token) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
