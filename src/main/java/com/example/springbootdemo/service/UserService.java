package com.example.springbootdemo.service;

import com.example.springbootdemo.bean.User;

import java.util.List;

/**
 *  @author XLZ
 *  @function : 用来做springboot与mybatis整合的练习 此处为业务层
 *  @tips : 这里没有添加@service的注解 因为可以在application中统一添加扫描项
 */

public interface UserService {

    User getUserById(Integer id);

    public List<User> getUserList();

    public int add(User user);

    public int update(Integer id , User user);

    public int delete(Integer id);

}
