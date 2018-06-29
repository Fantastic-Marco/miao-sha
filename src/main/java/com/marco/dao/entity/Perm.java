package com.marco.dao.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by landun on 2018/6/29.
 */
public class Perm {
    private String id;
    private String permname;
    private String permtype;
    private String permstr;
    private Integer priority;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermname() {
        return permname;
    }

    public void setPermname(String permname) {
        this.permname = permname;
    }

    public String getPermtype() {
        return permtype;
    }

    public void setPermtype(String permtype) {
        this.permtype = permtype;
    }

    public String getPermstr() {
        return permstr;
    }

    public void setPermstr(String permstr) {
        this.permstr = permstr;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
