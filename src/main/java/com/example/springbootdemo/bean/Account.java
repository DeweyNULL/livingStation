package com.example.springbootdemo.bean;

/**
 * @ClassName Account
 * @Description TODO
 * @Author XLZ
 * @Date 2018/8/3 11:09
 * @Version :
 **/

public class Account {
    private String accountName;
    private String password;
    private String userEmailAddress;
    private Integer accountStatus;

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmailAddress() {
        return userEmailAddress;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountName='" + accountName + '\'' +
                ", password='" + password + '\'' +
                ", userEmailAddress='" + userEmailAddress + '\'' +
                ", accountStatus=" + accountStatus +
                '}';
    }

    public void setUserEmailAddress(String userEmailAddress) {
        this.userEmailAddress = userEmailAddress;
    }
}
