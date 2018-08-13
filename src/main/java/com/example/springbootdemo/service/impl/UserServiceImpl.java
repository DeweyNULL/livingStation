package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.bean.User;
import com.example.springbootdemo.mapper.UserMapper;
import com.example.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  @author XLZ
 *  @function : 用来springboot和mybatis的整合练习 此处是实现service接口
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper usermapper;

    @Override
    public User getUserById(Integer id) {
        return  usermapper.getUserById(id);
    }

    @Override
    public List<User> getUserList() {
        return usermapper.getUserList();
    }

    @Override
    public int add(User user) {
        return usermapper.add(user);
    }

    @Override
    public int update(Integer id, User user) {
        return usermapper.update(id,user);
    }

    @Override
    public int delete(Integer id) {
        return usermapper.delete(id);
    }
}
