package com.example.springbootdemo.tool;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName GlobaDefaultExceptionHandler
 * @Description 全局异常处理
 * @Author XLZ
 * @Date 2018/7/30 10:23
 * @Version :
 **/

@ControllerAdvice
public class GlobaDefaultExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public void defaultErrorHandler(HttpServletRequest req , Exception e){
        e.printStackTrace();
        System.out.println("GlobaDefaultExceptionHandler");
    }
}
