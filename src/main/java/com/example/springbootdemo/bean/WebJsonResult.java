package com.example.springbootdemo.bean;

import java.util.List;

/**
 * @ClassName WebJsonResult
 * @Description TODO
 * @Author XLZ
 * @Date 2018/8/5 20:36
 * @Version :
 **/

public class WebJsonResult {
    private Integer statusCode = null;

    private Object result = null;
    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }


    public  WebJsonResult status(Integer statusCode){
        this.statusCode = statusCode;
        return this;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }


}
