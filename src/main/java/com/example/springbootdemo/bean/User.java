package com.example.springbootdemo.bean;

import java.util.Date;

/**
 *  @author XLZ
 *  @function : 用来做mysql整合练习，其中本实体对应数据库user4springboottest中的tb_user
 */

public class User {
    private int id ;
    private String username;
    private int age ;
    private Date ctm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCtm() {
        return ctm;
    }

    public void setCtm(Date ctm) {
        this.ctm = ctm;
    }
}
