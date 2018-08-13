package com.example.springbootdemo.service;

import com.example.springbootdemo.bean.UserCollect;

import java.util.List;

public interface BaseWebCollectSerivce {

    public List<UserCollect> getUserCollection(String user);

    public List<UserCollect>getUserCollectionByUserAndType(UserCollect userCollect);

    public UserCollect getWebByUserAndWebSiteName(UserCollect userCollect);

    public int addUserCollectWeb(UserCollect userCollect);

    public int deleteUser(UserCollect userCollect);

    public int updateUserMakeName(UserCollect userCollect);

}
