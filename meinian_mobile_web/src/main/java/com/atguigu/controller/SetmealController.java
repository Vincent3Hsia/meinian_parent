package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Setmeal;
import com.atguigu.service.SetmealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference
    SetmealService setmealService;
    //移动端的控制器(查询所有的套餐信息)
    @RequestMapping("/getSetmeal.do")
    public Result getSetmeal(){
      List<Setmeal> setmealList= setmealService.getSetmeal();
        return new Result(true, MessageConstant.QUERY_SETMEALLIST_SUCCESS,setmealList);
    }
    @RequestMapping("/findById.do")
    public Result findById(Integer id){
        Setmeal setmeal= setmealService.findById(id);
        return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
    }
    @RequestMapping("/getSetmealById.do")
    public Result getSetmealById(Integer id){
        Setmeal setmeal= setmealService.getSetmealById(id);
        return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
    }
}
