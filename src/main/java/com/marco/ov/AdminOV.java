package com.marco.ov;

/**
 * Created by landun on 2018/6/29.
 */
public class AdminOV {
    private String name;
    private String password;
    private String imgcode;

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

    public String getImgcode() {
        return imgcode;
    }

    public void setImgcode(String imgcode) {
        this.imgcode = imgcode;
    }

    @Override
    public String toString() {
        return "AdminOV{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", imgcode='" + imgcode + '\'' +
                '}';
    }
}
