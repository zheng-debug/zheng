package com.example.demo.dao;


import com.example.demo.entity.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface UserMapper {


    @Select("select role.rolename from t_role role left join t_user user on user.role_id = role.id where user.username = #{username}")
    Set<String> getRoles(String username);

    @Select("select miss.permissionname from t_user user " +
            "   left join t_role role on role.id = user.role_id " +
            "   left join t_permission miss on miss.role_id = role.id" +
            "  where user.username=#{username}")
    Set<String> getPermission(String username);

    @Select("select * from t_user t where t.username=#{username}")
    TUser queryUser(String username);

}
