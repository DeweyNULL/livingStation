package com.example.springbootdemo;

import com.example.springbootdemo.bean.Account;
import com.example.springbootdemo.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName testLogin
 * @Description  springboot的测试
 * @Author XLZ
 * @Date 2018/8/4 11:31
 * @Version :
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootdemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class testLogin {
    @Autowired
    AccountService accountService;

    @Test
    public void testGetAccount(){
        try {
            Account tempAccoutn = accountService.getAccountByAccountName("???");
            Account tempAccoutn2 = accountService.getAccountByUserMailAddress("xlz@123.com");
            if(tempAccoutn != null || tempAccoutn2!=null){
                System.out.println("have such user!");
            }
            //System.out.println(tempAccoutn.getUserEmailAddress());
        }catch (Exception e){
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
