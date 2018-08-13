package com.example.springbootdemo.mapper;

import com.example.springbootdemo.bean.Invited;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.util.List;

public interface InvitationMapper {

    //获取可以使用的注册码
    @Select("SELECT invitedCode FROM t_invited WHERE isUsed = 0")
    public List<String> getInvitedCodeList();

    //根据邀请码获取邀请对象
    @Select("SELECT * FROM t_invited WHERE invitedCode = #{invitedCode}")
    public Invited getInvitedByCodeName(String invitedCode);

    @Update("UPDATE t_invited SET isUsed = 1 WHERE invitedCode = #{invitedCode]]}")
    public int uodateInviteCodeStatus(String invitedCode);
}
