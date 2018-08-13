package com.example.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName LogoutController
 * @Description  注销用的Controller
 * @Author XLZ
 * @Date 2018/8/4 14:49
 * @Version :
 **/

@Controller
@RequestMapping("/project-first")
public class LogoutController {

    @RequestMapping("/LogoutController")
    public String accountLogout (HttpServletRequest request , HttpServletResponse response){
        HttpSession session = request.getSession();
        session.removeAttribute("ACCOUNT_IN_SESSION");
        return "redirect:Login";
    }

}
