package com.example.springbootdemo.controller;

import com.example.springbootdemo.bean.Account;
import com.example.springbootdemo.bean.JsonResult;
import com.example.springbootdemo.bean.LoginJsonResult;
import com.example.springbootdemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName LoginController
 * @Description  登录相关功能用的类
 * @Author XLZ
 * @Date 2018/8/4 11:08
 * @Version :
 **/

@Controller
@RequestMapping("/project-first")
public class LoginController {
    @Autowired
    AccountService accountService;

    //控制页面跳转,跳转到登录页面
    @RequestMapping("/Login")
    public String jumoToLoginPage(){
        return "login";
    }

    //接收登录页面的参数，控制跳转
    // errorCode 记录
    // errorCode 0 没问题
    // errorCode 1 账号不存在
    // errorCode 2 密码错误
    @RequestMapping("/LoginController")
    @ResponseBody
    public ResponseEntity<LoginJsonResult> accountConfirm(@RequestBody Account account, HttpServletRequest request , HttpServletResponse response) throws IOException {
        LoginJsonResult r = new LoginJsonResult();
        int flag = 0;
        //try中内容用来查找账号是否存在 ， 可以用账号或者邮箱登录

        Account userAccount =null;

        try {
            String accountName = account.getAccountName();
            String accountMail = account.getUserEmailAddress();
            Account accountGetByName = null;
            Account accountGetByMail = null;

            if(accountName!=null) {
                 accountGetByName = accountService.getAccountByAccountName(accountName);
            }
            if(accountMail!=null){
                accountGetByMail = accountService.getAccountByUserMailAddress(accountMail);
            }

            if(accountGetByMail!=null){
                userAccount = accountGetByMail;
                flag++;
            }

            if (accountGetByName!=null){
                userAccount = accountGetByName;
                flag++;
            }
        }catch (Exception e){

        }
        //这里开始做密码校验
        if(flag==0){
            //如果没有账号就结束 设置返回的json对象
            //errorCode = 1;
            r.setStatusCode(1);
            r.setResult("No Account Error!");
        }else {
            String password = account.getPassword();
            if(password.equals(userAccount.getPassword())){
                //密码认证正确的情况
                r.setStatusCode(0);
                r.setResult("succeed!");

                HttpSession session = request.getSession();
                // 这里先把账号信息放在session中，以后可以考虑放在redis缓存中
                session.setAttribute("ACCOUNT_IN_SESSION",userAccount.getAccountName());//把账号信息设置到session中
                //设置用户在线为1 这里需要考虑一件事情 就是用户关闭浏览器以后这个修改怎么办
                //这里先空着 想想办法

            }else {
                //密码不相等
                r.setStatusCode(2);
                r.setResult("Error password!");
            }
        }

        return ResponseEntity.ok(r);
    }
}
