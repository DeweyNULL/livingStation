package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.bean.Account;
import com.example.springbootdemo.mapper.AccountMapper;
import com.example.springbootdemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName AccountServiceImpl
 * @Description TODO
 * @Author XLZ
 * @Date 2018/8/4 10:08
 * @Version :
 **/

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    AccountMapper accountMapper;

    @Override
    public Account getAccountByAccountName(String accountName) {
        return accountMapper.getByAccountName(accountName);
    }

    @Override
    public Account getAccountByUserMailAddress(String userEmailAddress) {
        return accountMapper.getByUserEmailAddress(userEmailAddress);
    }

    @Override
    public int addAccount(Account account) {

        return accountMapper.addAccount(account);
    }

    @Override
    public int updateAccount(Account account) {
        return accountMapper.updatePassword(account);
    }

    @Override
    public int updateAccountStatus(Account account){
        return accountMapper.updateAccountStatus(account);
    }
}
