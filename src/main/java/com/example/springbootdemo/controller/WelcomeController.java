package com.example.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName WelcomeController
 * @Description  测试用用的类
 * @Author XLZ
 * @Date 2018/7/29 20:49
 * @Version :
 **/

@Controller
public class WelcomeController {
    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping("/index")
    public String index(){return "cover" ;}

    @RequestMapping("/testadd")
    public String testadd(){return "testAdd";}
}
