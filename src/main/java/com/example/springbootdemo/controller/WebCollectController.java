package com.example.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.example.springbootdemo.bean.*;
import com.example.springbootdemo.service.DYWebCollectService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @ClassName WebController
 * @Description TODO
 * @Author XLZ
 * @Date 2018/8/5 20:26
 * @Version :
 **/

@Controller
@RequestMapping("/project-first")
public class WebCollectController {
    @Autowired
    DYWebCollectService dyWebCollectService;

    @RequestMapping("/mainpage")
    public String toMainPage(HttpServletRequest request , HttpServletResponse response){
        return "mainPage";
    }

    //添加收藏站点
    //添加时前端整合好json 其中前端页面输入只给房间号，这里需要进行一个测试
    //如果房间号有问题应该不让添加
    //statusCode = 1的时候说明房间号有问题
    //statusCode = 2的时候说明房间已经添加过了
    @RequestMapping("/WebCollectAdd")
    public ResponseEntity<WebJsonResult> addCollection(@RequestBody UserCollect userCollect, HttpServletRequest request , HttpServletResponse response){
        WebJsonResult r = new WebJsonResult();
        System.out.println(userCollect);
        //对来存储的房间进行一次访问
        String tempJsonStr = dyWebCollectService.getUrLApiJSON(userCollect.getWebAPI());
        Map mapTypes = JSON.parseObject(tempJsonStr);
        for (Object obj : mapTypes.keySet()){
                    System.out.println("key为："+obj+"值为："+mapTypes.get(obj));
        }
        //根据返回map的error来查看 0正常  非0不存在或者未开通
        if(mapTypes.get("error").equals(0)){
            //在这之前还需要判断一下这个房间是不是存过了
            UserCollect tempUser = dyWebCollectService.getWebByUserAndWebSiteName(userCollect);
            if(tempUser!=null){
                r.setStatusCode(2);
                r.setResult("房间已经添加过了!");
            }else {
                dyWebCollectService.addUserCollectWeb(userCollect);
                r.setStatusCode(0);
                //r.setResult("OK");
                //添加完以后返回刚添加的房间的信息 给前端页面去加载
                String tempJson = dyWebCollectService.getUrLApiJSON(userCollect.getWebAPI());
                Map map = JSON.parseObject(tempJsonStr);
                map.put("webSiteName",userCollect.getUserCollectWebsite());
                r.setResult(map);
            }
        }
        else {
            r.setStatusCode(1);
            r.setResult("房间不存在或者未开通");
        }
        return  ResponseEntity.ok(r);
    }

    //此处已经是数据库中的
    //将用户收藏的斗鱼网页
    //statusCode 状态码
    //statusCode 0  一切正常
    //statusCode 1  用户没有收藏网站
    @RequestMapping("/getWebPage")
    public ResponseEntity<WebJsonResult> getWebCollection(@RequestBody UserCollectMiniType userCollectMiniType, HttpServletRequest request , HttpServletResponse response){
        WebJsonResult r = new WebJsonResult();

        //type应该要做出来 这里还没有区分是否是斗鱼的
        List<UserCollect> userCollects =dyWebCollectService.getUserCollection(userCollectMiniType.getAccountName());
        if (userCollects.size()==0){
            r.setStatusCode(1); //这是没有收藏的情况
            r.setResult(null);
        }else {
            //这里要循环
            int size = userCollects.size();
            List<Map> resultlist =new ArrayList<>();
            for (int i = 0; i<size;i++){
                String tempJsonStr = dyWebCollectService.getUrLApiJSON(userCollects.get(i).getWebAPI());
                Map mapTypes = JSON.parseObject(tempJsonStr);//这里的方法要记住
//                mapTypes.remove("data.gift");
                mapTypes.put("webSiteName",userCollects.get(i).getUserCollectWebsite());
//                for (Object obj : mapTypes.keySet()){
//                    System.out.println("key为："+obj+"值为："+mapTypes.get(obj));
//                }
                resultlist.add(mapTypes);
            }
            r.setStatusCode(0);
            r.setResult(resultlist);
        }
        return ResponseEntity.ok(r);
    }

    //前端用来获取用户的信息
    @RequestMapping(value = "/getUserInfo",method = {RequestMethod.GET})
    public ResponseEntity<JsonResult> getUserInfo(HttpServletRequest request , HttpServletResponse response){
        JsonResult result = new JsonResult();
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("ACCOUNT_IN_SESSION");
        //System.out.println(session.getAttribute("ACCOUNT_IN_SESSION"));
        if (username!=null){
            result.setStatus("0");
            result.setResult(username);
        }else {
            result.setStatus("1");
            result.setResult(null);
        }

        return ResponseEntity.ok(result);
    }

    //删除房间
    @RequestMapping("/deleteWeb")
    public  ResponseEntity<JsonResult> deleteWeb(@RequestBody UserCollect userCollect ,HttpServletRequest request , HttpServletResponse response){
        JsonResult result = new JsonResult();
        int num = dyWebCollectService.deleteUser(userCollect);
        result.setStatus(String.valueOf(num));
        return ResponseEntity.ok(result);
    }

}
