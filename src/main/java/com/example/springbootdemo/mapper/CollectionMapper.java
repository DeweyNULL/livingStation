package com.example.springbootdemo.mapper;

import com.example.springbootdemo.bean.UserCollect;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CollectionMapper  {


    //根据用户名获取用户所有收藏的网站
    @Select("SELECT * FROM t_usercollect WHERE user = #{user}")
    public List<UserCollect> getUserCollection(String user);

    //根据用户名和网站类型获取用户收藏的网站
    @Select("SELECT * FROM t_usercollect WHERE user = #{user} AND userCollectWebsiteType = #{userCollectWebsiteType}")
    public List<UserCollect>getUserCollectionByUserAndType(UserCollect userCollect);

    //根据用户名和网站名获取网站
    @Select("SELECT * FROM t_usercollect WHERE user = #{user} AND userCollectWebsite = #{userCollectWebsite}")
    public UserCollect getWebByUserAndWebSiteName(UserCollect userCollect);

    //添加用户收藏网站
    @Insert("INSERT INTO t_usercollect(user,userCollectWebsite,userCollectWebsiteType,userMakeName,WebAPI) " +
            "VALUES(#{user},#{userCollectWebsite},#{userCollectWebsiteType}" +
            ",#{userMakeName},#{webAPI})")
    public int addUserCollectWeb(UserCollect userCollect);

    //删除某个用户收藏网站
    @Delete("DELETE FROM t_usercollect WHERE user=#{user}" +
            "AND userCollectWebsite=#{userCollectWebsite}")
    public int deleteUser(UserCollect userCollect);

    //修改用户拟定名字
    @Update("UPDATE t_usercollect SET userMakeName=#{userCollect.userMakeName} WHEER user=#{userCollect.user}" +
            "AND userCollectWebsite=#{userCollect.userCollectWebsite}")
    public int updateUserMakeName(UserCollect userCollect);
}
