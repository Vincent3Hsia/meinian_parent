package com.atguigu.dao;

import com.atguigu.pojo.Order;

import java.util.Map;

public interface OrderDao {
    Order getOrder(Map param);

    void add(Order order);

    Map findById(Integer id);
}
