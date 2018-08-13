package com.example.springbootdemo.bean;

/**
 * @ClassName Invited
 * @Description TODO
 * @Author XLZ
 * @Date 2018/8/3 11:12
 * @Version :
 **/

public class Invited {
    private String invitedCode;
    private Integer isUsed;

    public String getInvitedCode() {
        return invitedCode;
    }

    public void setInvitedCode(String invitedCode) {
        this.invitedCode = invitedCode;
    }

    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }
}
