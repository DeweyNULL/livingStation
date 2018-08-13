package com.example.springbootdemo.bean;

/**
 * @ClassName RegisterInfo
 * @Description TODO
 * @Author XLZ
 * @Date 2018/8/4 16:49
 * @Version :
 **/

public class RegisterInfo {
    private Invited invited;
    private Account account;

    public Invited getInvited() {
        return invited;
    }

    public void setInvited(Invited invited) {
        this.invited = invited;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


}
