package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.service.OrderSettingService;
import com.atguigu.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
     @Reference
    OrderSettingService orderSettingService;
    //上传文件
    @RequestMapping("/upload.do")
    public Result upload(MultipartFile excelFile) throws IOException {
        List<String[]> strings = POIUtils.readExcel(excelFile);//读取模板文件到集合中
        //把集合中的数据保存到数据库中  String[] 等价于 一行数据
        List<OrderSetting> orderSettingList=new ArrayList<OrderSetting>();
        for (String[] string : strings) {
             OrderSetting orderSetting=new OrderSetting();
             orderSetting.setOrderDate(new Date(string[0]));
             orderSetting.setNumber(Integer.parseInt(string[1]));
             orderSettingList.add(orderSetting);
        }
        try {
            orderSettingService.add(orderSettingList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
        return new Result(true, MessageConstant.UPLOAD_SUCCESS);
    }
    @RequestMapping("/getOrderSettingByMonth.do")
    public Result getOrderSettingByMonth(String date){
          List<Map> orderSettingList= orderSettingService.getOrderSettingByMonth(date);
          return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,orderSettingList);
    }
    @RequestMapping("/editNumberByDate.do")
    public Result editNumberByDate(@RequestBody  OrderSetting orderSetting){
        try {
            orderSettingService.editNumberByDate(orderSetting);
        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }
        return  new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
    }
}
