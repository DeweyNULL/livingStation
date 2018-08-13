package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.bean.UserCollect;
import com.example.springbootdemo.mapper.CollectionMapper;
import com.example.springbootdemo.service.UserCollectionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName UserCollecctionServiceImpl
 * @Description TODO
 * @Author XLZ
 * @Date 2018/8/4 11:04
 * @Version :
 **/

public class UserCollecctionServiceImpl implements UserCollectionService {

    @Autowired
    CollectionMapper collectionMapper;
    @Override
    public List<UserCollect> getUserCollection(String user) {
        return collectionMapper.getUserCollection(user);
    }

    @Override
    public int addUserCollectWeb(UserCollect userCollect) {
        return collectionMapper.addUserCollectWeb(userCollect);
    }

    @Override
    public int deleteUserCollectWeb(UserCollect userCollect) {
        return collectionMapper.deleteUser(userCollect);
    }

    @Override
    public int updateUserMakeName(UserCollect userCollect) {
        return collectionMapper.updateUserMakeName(userCollect);
    }
}
