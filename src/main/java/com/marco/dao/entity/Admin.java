package com.marco.dao.entity;

/**
 * Created by landun on 2018/5/23.
 */

public class Admin {
    private Integer id;
    private String name;
    private String passsword;
    private String token;

    public Admin() {
    }

    public Admin(Integer id, String name, String passsword, String token) {
        this.id = id;
        this.name = name;
        this.passsword = passsword;
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

    public String getPasssword() {
        return passsword;
    }

    public void setPasssword(String passsword) {
        this.passsword = passsword;
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
                ", passsword='" + passsword + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
