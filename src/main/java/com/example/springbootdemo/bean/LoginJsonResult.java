package com.example.springbootdemo.bean;

/**
 * @ClassName LoginJsonResult
 * @Description TODO
 * @Author XLZ
 * @Date 2018/8/4 14:32
 * @Version :
 **/

public class LoginJsonResult {
    private Integer statusCode = null;
    private Object result = null;

    public  LoginJsonResult status(Integer status){
        this.statusCode = status;
        return this;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
