package com.marco.dao;

import com.marco.dao.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by landun on 2018/5/23.
 */
@Mapper
public interface AdminMapper {

    @Select("select * from admin")
    List<Admin> getAll();
}
