package com.example.springbootdemo.service;

import com.example.springbootdemo.bean.Invited;

import java.util.List;

public interface InviteService {

    public List<String> getInvitationCode();

    public Invited getInvitedCodeStatus(String invitedCode);

    public int updateStatus(String invitedCode);


}
