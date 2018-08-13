package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.bean.Invited;
import com.example.springbootdemo.mapper.InvitationMapper;
import com.example.springbootdemo.service.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName InvitationServiceImpl
 * @Description TODO
 * @Author XLZ
 * @Date 2018/8/4 10:19
 * @Version :
 **/

@Service
public class InvitationServiceImpl implements InviteService {

    @Autowired
    InvitationMapper invitationMapper;
    @Override
    public List<String> getInvitationCode() {
        return  invitationMapper.getInvitedCodeList();
    }

    @Override
    public Invited getInvitedCodeStatus(String invitedCode) {
        return  invitationMapper.getInvitedByCodeName(invitedCode);
    }

    @Override
    public int updateStatus(String invitedCode) {
        return invitationMapper.uodateInviteCodeStatus(invitedCode);
    }
}
