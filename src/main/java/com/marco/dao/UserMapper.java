package com.marco.dao;

import com.marco.dao.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by landun on 2018/7/4.
 */
@Mapper
public interface UserMapper {

    @Select("select * from users where id = #{id} limit 1")
    SysUser getUserById(@Param("id") String id);
}
