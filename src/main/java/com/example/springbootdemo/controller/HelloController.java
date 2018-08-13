package com.example.springbootdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *  description:最开始用来测试的类
 */
@RestController

public class HelloController {
    @RequestMapping("/building")
    public String hellotest(){
        return "is building......";
    }


}
