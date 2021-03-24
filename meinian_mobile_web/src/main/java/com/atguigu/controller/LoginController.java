package com.atguigu.controller;

import com.atguigu.constant.RedisMessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    JedisPool jedisPool;
    @RequestMapping("/check.do")
    public Result check(@RequestBody Map map){
        //获取手机号
        String telephone = map.get("telephone").toString();
        //获取验证码
        String validateCode = map.get("validateCode").toString();
        //获取redis中的验证码
        String redisCode = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_LOGIN);
        //比较
        if(redisCode!=null||redisCode.equals(validateCode)){
            return new Result(true,"登录成功");
        }else{
            return new Result(false,"登录失败");
        }
    }
}
