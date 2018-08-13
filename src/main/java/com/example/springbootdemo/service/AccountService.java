package com.example.springbootdemo.service;

import com.example.springbootdemo.bean.Account;
import org.springframework.beans.factory.annotation.Autowired;

public interface AccountService {

    //根据账号名获取账号的功能
    public Account getAccountByAccountName(String accountName);

    //根据账号的邮箱获取账号的功能
    public Account getAccountByUserMailAddress(String userEmailAddress);

    //注册功能
    public int addAccount(Account account);

    //修改密码
    public int updateAccount(Account account);

    //修改登录状态
    public int updateAccountStatus(Account account);
}
