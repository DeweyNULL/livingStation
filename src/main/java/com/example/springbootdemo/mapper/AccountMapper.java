package com.example.springbootdemo.mapper;

import com.example.springbootdemo.bean.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 *  @author XLZ
 *  @function : 平台的账号操作mapper 包括增删改查
 *  tips : 这里没有添加@mapper的注解 因为可以在application中统一添加扫描项
 */

public interface AccountMapper {

    //根据账号名获取账号信息
    @Select("SELECT * FROM t_account WHERE accountName = #{accountName}")
    public Account getByAccountName(String accountName);


    //根据账号的邮箱地址获取账号
    @Select("SELECT * FROM t_account WHERE userEmailAddress = #{userEmailAddress}")
    public Account getByUserEmailAddress(String userEmailAddress);


    //注册账号功能的添加账号
    @Insert("INSERT INTO t_account(accountName,password,userEmailAddress,accountStatus) " +
            "VALUES(#{accountName},#{password},#{userEmailAddress},#{accountStatus}) ")
    public int addAccount(Account account);

    //修改密码的时候的功能
    @Update("UPDATE t_account SET password=#{account.password} WHERE accountName = #{account.accountName}")
    public int updatePassword(Account account);

    //登录的时候修改登录状态 以及注销的时候修改登录状态
    @Update("UPDATE t_account SET accountStatus={#account.accountStatus} WHERE accountName=#{account.accountName}")
    public int updateAccountStatus(Account account);
}
