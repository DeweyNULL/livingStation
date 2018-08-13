package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.service.DYWebCollectService;
import org.springframework.stereotype.Service;

import java.net.URL;

/**
 * @ClassName DYWebCollectService
 * @Description TODO
 * @Author XLZ
 * @Date 2018/8/5 20:16
 * @Version :
 **/

@Service
public class DYWebCollectServiceImpl extends BaseWebCollectServiceImpl implements DYWebCollectService {

    //功能是根据传来的web号解析出房间号 访问
    @Override
    public String getUrLApiJSON(String APIUrl) {
        String Result;
        try {
            URL url = new URL(APIUrl);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            String json ="";
            while ((line = in.readLine()) != null) {
                json+=line;
                //  System.out.println(line);
            }
            Result = json;
            System.out.println(json);
            // 到这里需要一个json字符串转json对象的包 先暂停
            in.close();
        }catch (Exception e){
            e.printStackTrace();
            Result = null;
        }
        return Result;
    }
}
