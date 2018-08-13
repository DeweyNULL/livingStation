package com.example.springbootdemo.controller;

import com.example.springbootdemo.bean.JsonResult;
import com.example.springbootdemo.bean.User;
import com.example.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 *  @author XLZ
 *  @function : 这是springboot和mybatis的整合练习 ， 此处为mvc的controller层
 *
 */

@RestController
//@RequestMapping("/user")  存疑点1
public class UserController {
    @Autowired
    private UserService userService;

    /**
     *  根据user的id来查询用户
     *  @param id
     *  @return
     */

    //value等于后面的值  存疑点2
    //此处responseEntity 和 pathvariable 两个注解 存疑点3
    @RequestMapping(value = "user/{id}",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getUserById(@PathVariable(value = "id") Integer id){
        JsonResult r = new JsonResult();

        try {
            User user = userService.getUserById(id);
            r.setResult(user);
            r.setStatus("ok");
        }catch (Exception e){
            r.setResult(e.getClass().getName()+":"+e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    /**
     *  查询用户列表
     *  @return
     */
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getUserList(){
        JsonResult r = new JsonResult();
        try {
            List<User> users = userService.getUserList();
            r.setStatus("ok");
            r.setResult(users);

        }catch (Exception e){
            r.setResult(e.getClass().getName()+" : "+e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    /**
     *  添加用户
     *  @param user
     *  @return
     */
    @RequestMapping(value = "user",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> add(@RequestBody User user){
        JsonResult r = new JsonResult();
        try {
            int orderId = userService.add(user);
            r.setStatus("ok");
            r.setResult(orderId);
        }catch (Exception e){
            r.setStatus("error");
            r.setResult(e.getClass().getName()+" : "+e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);

    }

    /**
     *  根据id修改用户信息
     *  @param user
     *  @return
     */
    @RequestMapping(value = "user/{id}",method = RequestMethod.POST )
    public ResponseEntity<JsonResult> update(@PathVariable("id") Integer id , @RequestBody User user){
        JsonResult r = new JsonResult();
        try {
            int orderId = userService.update(id,user);
            r.setResult(orderId);
            r.setStatus("ok");
        }catch (Exception e){
            r.setStatus("error");
            r.setResult(e.getClass().getName()+" : "+e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    /**
     *  
     */

}
