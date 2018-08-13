package com.example.springbootdemo.controller;

import com.example.springbootdemo.bean.Account;
import com.example.springbootdemo.bean.Invited;
import com.example.springbootdemo.bean.LoginJsonResult;
import com.example.springbootdemo.bean.RegisterInfo;
import com.example.springbootdemo.service.AccountService;
import com.example.springbootdemo.service.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName RegisterController
 * @Description TODO
 * @Author XLZ
 * @Date 2018/8/4 16:37
 * @Version :
 **/

@Controller
@RequestMapping("/project-first")
public class RegisterController {

    @Autowired
    InviteService inviteService;

    @Autowired
    AccountService accountService;
    //注册页面跳转
    @RequestMapping("/register")
    public String toRegisterPage(){
        return "SignUp";
    }

    //statusCode=0 表示注册通过
    //statuscode = 1 表示注册码有问题
    //statuscode = 2 表示用户已经被注册
    // statuscode = 3 表示邮箱被使用
    //注册的信息表
    //这里的注册信息包含了账号信息和邀请码信息
    @RequestMapping("/registerController")
    @ResponseBody
    public ResponseEntity<LoginJsonResult> register(@RequestBody RegisterInfo registerInfo, HttpServletRequest request, HttpServletResponse response){
        LoginJsonResult r = new LoginJsonResult();
        Account account = registerInfo.getAccount();
        Invited invited = registerInfo.getInvited();
        boolean isUsed = false;

        //先查询注册邀请码是否可用
        Invited tempInvited = inviteService.getInvitedCodeStatus(invited.getInvitedCode());
        Account accountByName = accountService.getAccountByAccountName(account.getAccountName());
        Account accountByMail = accountService.getAccountByUserMailAddress(account.getUserEmailAddress());

        if(tempInvited != null){
            if(tempInvited.getIsUsed() == 1){
                isUsed=true; //用过了的邀请码设置已经使用过
            }
        }else {
            //验证码不存在的情况
            isUsed = true;
        }


        if (!isUsed){ // 邀请码可以使用的情况
            r.setStatusCode(0);
            //接下来判断账号可用情况
            if(accountByMail==null && accountByName == null){
                //如果都为空 则可以注册
                //System.out.println(account);
                accountService.addAccount(account); //添加账号

                inviteService.updateStatus(invited.getInvitedCode()); //修改邀请码状态
                r.setResult("注册成功！");
            }else if(accountByMail!=null){
                //如果邮箱地址不为空
                r.setStatusCode(3);
                r.setResult("邮箱地址已经被注册！");
            }else if(accountByName!=null){
                //如果用户名不为空
                r.setStatusCode(2);
                r.setResult("账号已经被注册！");
            }
        }else {
            //邀请码不可用的情况
            r.setStatusCode(1);
            r.setResult("邀请码不可用！");
        }

        return ResponseEntity.ok(r);
    }
}
