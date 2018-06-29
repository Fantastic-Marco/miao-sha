package com.marco.dao.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by landun on 2018/6/29.
 */
public class Role {
    private String id;
    private String rolename;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
