package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.TravelgroupDao;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.TravelGroup;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = TravelgroupService.class)
@Transactional
public class TravelgroupServiceImpl implements TravelgroupService {
    @Autowired
    TravelgroupDao travelgroupDao;
    public void add(Integer[] travelItemIds, TravelGroup travelGroup) {
        //1.添加跟团游数据
           travelgroupDao.add(travelGroup);
        //2. 添加跟团游和自由行的关系数据到关系表
           insert_travelGroup_travelItem(travelGroup.getId(),travelItemIds);
    }

    public PageResult findPage(QueryPageBean queryPageBean) {
        //分页
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<TravelGroup> page=travelgroupDao.findPage(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(),page.getResult());
    }

    public TravelGroup findTravegroup(Integer id) {
        return travelgroupDao.findTravegroup(id);
    }

    public List<Integer> findTravelItemIds(Integer id) {
        return  travelgroupDao.findTravelItemIds(id);
    }
      //[1,2] [3,4]
    public void edit(Integer[] travelItemIds, TravelGroup travelGroup) {
        //修改跟团游
         travelgroupDao.edit(travelGroup);
         //删除关系表中当前跟团游的历史数据
        travelgroupDao.delete(travelGroup.getId());
        //添加跟团游对应的自由行id信息
         insert_travelGroup_travelItem(travelGroup.getId(),travelItemIds);
    }

    public List<TravelGroup> findAll() {
        return travelgroupDao.findAll();
    }

    public void insert_travelGroup_travelItem(Integer travelGroupId,Integer[] travelItemIds){
        for (Integer travelItemId : travelItemIds) {
            Map map=new HashMap();
            map.put("travelGroupId",travelGroupId);
            map.put("travelItemId",travelItemId);
            travelgroupDao.insert_travelGroup_travelItem(map);
        }
    }
}
