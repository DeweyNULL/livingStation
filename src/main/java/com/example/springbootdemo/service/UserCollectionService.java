package com.example.springbootdemo.service;

import com.example.springbootdemo.bean.UserCollect;

import java.util.List;

public interface UserCollectionService {
    public List<UserCollect> getUserCollection(String user);

    public int addUserCollectWeb(UserCollect userCollect);

    public int deleteUserCollectWeb(UserCollect userCollect);

    public int updateUserMakeName(UserCollect userCollect);
}
