package com.example.springbootdemo.bean;

/**
 * @ClassName UserCollect
 * @Description TODO
 * @Author XLZ
 * @Date 2018/8/3 11:15
 * @Version :
 **/

public class UserCollect {
    private Long id;
    private String user;
    private String userCollectWebsite;
    private String userCollectWebsiteType;
    private String userMakeName;
    private String webAPI;

    public String getWebAPI() {
        return webAPI;
    }

    public void setWebAPI(String webAPI) {
        this.webAPI = webAPI;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserCollectWebsite() {
        return userCollectWebsite;
    }

    public void setUserCollectWebsite(String userCollectWebsite) {
        this.userCollectWebsite = userCollectWebsite;
    }

    public String getUserCollectWebsiteType() {
        return userCollectWebsiteType;
    }

    public void setUserCollectWebsiteType(String userCollectWebsiteType) {
        this.userCollectWebsiteType = userCollectWebsiteType;
    }

    public String getUserMakeName() {
        return userMakeName;
    }

    public void setUserMakeName(String userMakeName) {
        this.userMakeName = userMakeName;
    }

    @Override
    public String toString() {
        return "UserCollect{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", userCollectWebsite='" + userCollectWebsite + '\'' +
                ", userCollectWebsiteType='" + userCollectWebsiteType + '\'' +
                ", userMakeName='" + userMakeName + '\'' +
                ", webAPI='" + webAPI + '\'' +
                '}';
    }
}
