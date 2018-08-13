package com.example.springbootdemo.controller;

import com.example.springbootdemo.bean.TestProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  @author XLZ
 *  @function: 这是用来测试yml中写类的类
 */

@RestController
public class TestYMLClassController {

    @Autowired
    private TestProperties properties;

    @RequestMapping("/hello3")
    public String testYml(){

        return properties.getToString();
    }
}
