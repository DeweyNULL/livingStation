package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.bean.UserCollect;
import com.example.springbootdemo.mapper.CollectionMapper;
import com.example.springbootdemo.service.BaseWebCollectSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName BaseWebCollectServiceImpl
 * @Description TODO
 * @Author XLZ
 * @Date 2018/8/5 20:12
 * @Version :
 **/

@Service
public class BaseWebCollectServiceImpl implements BaseWebCollectSerivce {
    @Autowired
    CollectionMapper collectionMapper;

    @Override
    public List<UserCollect> getUserCollection(String user) {
        return collectionMapper.getUserCollection(user);
    }

    @Override
    public List<UserCollect> getUserCollectionByUserAndType(UserCollect userCollect) {
        return collectionMapper.getUserCollectionByUserAndType(userCollect);
    }

    @Override
    public UserCollect getWebByUserAndWebSiteName(UserCollect userCollect) {
        return collectionMapper.getWebByUserAndWebSiteName(userCollect);
    }

    @Override
    public int addUserCollectWeb(UserCollect userCollect) {
        return collectionMapper.addUserCollectWeb(userCollect);
    }

    @Override
    public int deleteUser(UserCollect userCollect) {
        return collectionMapper.deleteUser(userCollect);
    }

    @Override
    public int updateUserMakeName(UserCollect userCollect) {
        return collectionMapper.updateUserMakeName(userCollect);
    }
}
