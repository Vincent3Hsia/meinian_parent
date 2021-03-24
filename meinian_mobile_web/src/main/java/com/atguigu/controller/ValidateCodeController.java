package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.constant.RedisMessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.utils.SMSUtils;
import com.atguigu.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {
     @Autowired
    JedisPool jedisPool;


    @RequestMapping("send4Order.do")
    public Result send4Order(String telephone) throws Exception {
         //生成验证码
        Integer code = ValidateCodeUtils.generateValidateCode(4);
        //发送短信
        SMSUtils.sendShortMessage(telephone,code.toString());
        //把验证码保存到redis中 10分钟
       jedisPool.getResource().setex(telephone+RedisMessageConstant.SENDTYPE_ORDER,10*60,code.toString());

        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }

    @RequestMapping("/send4Login.do")
    public Result send4Login(String telephone) throws Exception {
        //生成手机号对应的验证码
        Integer code = ValidateCodeUtils.generateValidateCode(4);
        //发送验证码到手机
        SMSUtils.sendShortMessage(telephone,code.toString());
        //redis中保存验证码
        jedisPool.getResource().setex(telephone+RedisMessageConstant.SENDTYPE_LOGIN,5*60,code.toString());
        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }
}
