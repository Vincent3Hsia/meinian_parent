package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.TravelGroup;
import com.atguigu.service.TravelgroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/travelgroup")
public class TravelgroupController {
    @Reference
    TravelgroupService travelgroupService;
     @RequestMapping("/add.do")
    public Result add(Integer[] travelItemIds, @RequestBody TravelGroup travelGroup){
         try {
             travelgroupService.add(travelItemIds,travelGroup);
         } catch (Exception e) {
             e.printStackTrace();
             return new Result(false, MessageConstant.ADD_TRAVELGROUP_FAIL);
         }
         return new Result(true, MessageConstant.ADD_TRAVELGROUP_SUCCESS);
     }
     @RequestMapping("/findPage.do")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
           PageResult pageResult=  travelgroupService.findPage(queryPageBean);
           return pageResult;
     }
     //根据跟团游的id查询数据实现页面的回显
     @RequestMapping("/findTravegroup.do")
    public Result findTravegroup(Integer id){
         TravelGroup travelGroup= travelgroupService.findTravegroup(id);
         return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,travelGroup);
     }
     //跟团游关联的自由行id查询
     @RequestMapping("/findTravelItemIds.do")
    public Result findTravelItemIds(Integer id){
         List<Integer> ids= travelgroupService.findTravelItemIds(id);
         return new Result(true,"查询id成功",ids);
     }
     @RequestMapping("/edit.do")
    public Result edit(Integer[] travelItemIds,@RequestBody TravelGroup travelGroup){
         try {
             travelgroupService.edit(travelItemIds,travelGroup);
         } catch (Exception e) {
             e.printStackTrace();
             return new Result(false,MessageConstant.EDIT_TRAVELGROUP_FAIL);
         }
         return new Result(true,MessageConstant.EDIT_TRAVELGROUP_SUCCESS);
     }
     @RequestMapping("/findAll.do")
    public Result findAll(){
       List<TravelGroup> travelGroupList=  travelgroupService.findAll();
       return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,travelGroupList);
     }
}
