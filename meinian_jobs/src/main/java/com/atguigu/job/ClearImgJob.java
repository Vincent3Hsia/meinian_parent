package com.atguigu.job;

import com.atguigu.constant.RedisConstant;
import com.atguigu.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

public class ClearImgJob {
     @Autowired
    JedisPool jedisPool;
    //定时清理七牛云服务器中的图片
    //清理redis中的图片名字
    public void clearImg(){
        Jedis jedis = jedisPool.getResource();
        Set<String> sdiff = jedis.sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        for (String imgFileName : sdiff) {
             //删除七牛云
            QiniuUtils.deleteFileFromQiniu(imgFileName);
            //删除redis中的图片
             jedis.srem(RedisConstant.SETMEAL_PIC_RESOURCES,imgFileName);
        }
        System.out.println("删除成功");
        jedis.close();

    }
}
