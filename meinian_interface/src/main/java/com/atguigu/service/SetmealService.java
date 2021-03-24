package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.Setmeal;

import java.util.List;

public interface SetmealService {
    public void add(Integer[] travelgroupIds,Setmeal setmeal);

    PageResult findPage(QueryPageBean queryPageBean);

    //移动端的查询
    List<Setmeal> getSetmeal();

    Setmeal findById(Integer id);

    Setmeal getSetmealById(Integer id);
}
