package com.marco.dao;

import com.marco.dao.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by landun on 2018/7/4.
 */
@Mapper
public interface RoleMapper {

    @Select("SELECT\n" +
            "\tr.*\n" +
            "FROM\n" +
            "\troles r\n" +
            "INNER JOIN users_roles ur ON ur.roles_id = r.id\n" +
            "INNER JOIN users u ON ur.users_id = u.id\n" +
            "WHERE\n" +
            "\tu.id = #{id}")
    List<Role> getRolesByUserId(@Param("id") String id);
}
