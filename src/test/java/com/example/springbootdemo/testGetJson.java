package com.example.springbootdemo;

import org.json.JSONObject;
import org.junit.Test;

import java.net.URL;
import java.sql.ResultSet;

/**
 * @ClassName testGetJson
 * @Description TODO
 * @Author XLZ
 * @Date 2018/8/1 20:40
 * @Version :
 **/

public class testGetJson {
    @Test
    public void testGetJson(){

        String Url = "http://open.douyucdn.cn/api/RoomApi/room/9999";

        try {
            URL url = new URL(Url);
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
            System.out.println(json);
            // 到这里需要一个json字符串转json对象的包 先暂停
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
