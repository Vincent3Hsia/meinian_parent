package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.OrderSettingDao;
import com.atguigu.pojo.OrderSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
     @Autowired
    OrderSettingDao orderSettingDao;
    public void add(List<OrderSetting> orderSettingList) {
            if(orderSettingList!=null){
                for (OrderSetting orderSetting : orderSettingList) {
                    //查询当前日期有没有提前预约
                   long count=  orderSettingDao.findOrderSettingByOrderDate(orderSetting.getOrderDate());
                    if(count>0){
                          orderSettingDao.editOrderSettingByOrderDate(orderSetting);
                    }else {
                        orderSettingDao.add(orderSetting);
                    }
                }
            }
    }

    //根据日期查询预约设置 (当前月份)
    public List<Map> getOrderSettingByMonth(String date) {//like 2021-03%
          List<OrderSetting> orderSettings=  orderSettingDao.getOrderSettingByMonth(date);
          List<Map> listMap=new ArrayList<Map>();
        for (OrderSetting orderSetting : orderSettings) {
             Map map=new HashMap();
             map.put("date",orderSetting.getOrderDate().getDate());//2021-03-16
             map.put("number",orderSetting.getNumber());
             map.put("reservations",orderSetting.getReservations());
             listMap.add(map);
        }
        return listMap;
    }

    public void editNumberByDate(OrderSetting orderSetting) {
       OrderSetting orderSettingDB= orderSettingDao.findOrderSettingObjectByOrderDate(orderSetting.getOrderDate());
        if(orderSettingDB==null){//该日期还没有被设置
              orderSettingDao.add(orderSetting);
        }else{
            if(orderSettingDB.getReservations()>orderSetting.getNumber()){//如果已预约的人数超过了可预约的人数
                throw new RuntimeException("不能进行设置");
            }else{
                 orderSettingDao.editOrderSettingByOrderDate(orderSetting);
            }
        }

    }
}
