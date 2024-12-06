package com.shijiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shijiu.dao.mapper.FoodMapper;
import com.shijiu.pojo.Food;
import com.shijiu.service.CateService;
import com.shijiu.service.FoodService;
import com.shijiu.utils.UserThreadLocal;
import com.shijiu.vo.FoodListVo;
import com.shijiu.vo.FoodVo;
import com.shijiu.vo.PageParams;
import com.shijiu.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FoodServiceImpl extends ServiceImpl<FoodMapper,Food> implements FoodService {

    @Autowired
    private FoodMapper foodMapper;
    @Autowired
    private CateService cateService;
    @Override
    public Result queryFoodList(PageParams pageParams) {
        Page<Food> page = new Page<>(pageParams.getPage(),pageParams.getPageSize());
        LambdaQueryWrapper<Food> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        Map map = UserThreadLocal.get();
        //取到对应店铺的id
//        System.out.println("queryFoodList打印店铺id"+map.get("shopId"));
        lambdaQueryWrapper.eq(Food::getShopId,map.get("shopId"));
        //查询这个店铺的所有菜品
        Page<Food> foodPage = foodMapper.selectPage(page, lambdaQueryWrapper);
        List<Food> foodList = foodPage.getRecords();
//        System.out.println("foodlist--"+foodList);
        List<FoodVo> foodVoList = new ArrayList<>();
        for (Food food : foodList) {
            FoodVo foodVo = new FoodVo();
            BeanUtils.copyProperties(food,foodVo);
            //根据food里面的cateId查询出cateName给foodVo赋值
            foodVo.setCategoryName(cateService.queryCateById(food.getCategoryId()).getName());
            foodVoList.add(foodVo);
        }
        System.out.println("foodVOlist--"+foodVoList);
        //foodListVo 包含foodVoList和total
        FoodListVo foodListVo = new FoodListVo();
        foodListVo.setFoodVoList(foodVoList);
        foodListVo.setTotal(foodPage.getTotal());
        return Result.success(foodListVo);
    }
}
