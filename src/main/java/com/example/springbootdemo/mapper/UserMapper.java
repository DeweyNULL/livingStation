package com.example.springbootdemo.mapper;

import com.example.springbootdemo.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 *  @author XLZ
 *  @function : 用来练习springboot整合mybatis 其中实现对数据库的增删改查功能
 *  tips : 这里没有添加@mapper的注解 因为可以在application中统一添加扫描项
 */

public interface UserMapper {

    @Select("SELECT * FROM tb_user WHERE id = #{id}")
    User getUserById(Integer id);

    @Select("SELECT * FROM tb_user")
    public List<User> getUserList();

    @Insert("insert into tb_user(username,age,ctm) values(#{username},#{age},now())")
    public int add(User user);

    //这里使用了@param的注解 在使用这个注解后 参数的占位符用$ 或者 #都可以 但是不适用只能用#
    //并且应该多用# 因为$会有被注入的风险
    @Update("update tb_user set username = #{user.username} , age = #{user.age} where id = #{id}")
    public int update(@Param("id") Integer id ,@Param("user") User user);

    @Delete("delete from tb_user where id = #{id}")
    public int delete(Integer id);

}
