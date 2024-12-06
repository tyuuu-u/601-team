package com.shijiuAPI.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shijiuAPI.dao.mapper.CateMapper;
import com.shijiuAPI.dao.mapper.FoodMapper;
import com.shijiuAPI.dao.mapper.ShopMapper;
import com.shijiuAPI.pojo.Category;
import com.shijiuAPI.pojo.Food;
import com.shijiuAPI.pojo.Shop;
import com.shijiuAPI.service.ShopService;
import com.shijiuAPI.vo.CateFoodsVo;
import com.shijiuAPI.vo.FoodVo;
import com.shijiuAPI.vo.Result;
import com.shijiuAPI.vo.ShopVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {

    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private CateMapper cateMapper;
    @Autowired
    private FoodMapper foodMapper;


    @Override
    public Result shopList() {
        LambdaQueryWrapper<Shop> qw = new LambdaQueryWrapper<>();
        //店主没关店的所有店铺,管理员没有下架,按照权重排序
        qw.eq(Shop::getClosed, false).eq(Shop::getIsShutDown, false).orderByDesc(Shop::getWeight);
        List<Shop> shops = shopMapper.selectList(qw);
        List<ShopVo> shopVos = new ArrayList<>();
        for (Shop shop : shops) {
            ShopVo shopVo = new ShopVo();
            BeanUtils.copyProperties(shop, shopVo);
            shopVos.add(shopVo);
        }
        log.info("sout shopvos {}", shopVos);
        return Result.success(shopVos);
    }

    @Override
    public Result cateFoodsList(Integer shopId) {
        //根据shopId查找店铺所有的分类
        LambdaQueryWrapper<Category> qw = new LambdaQueryWrapper<>();
        qw.eq(Category::getShopId, shopId);
        List<Category> categories = cateMapper.selectList(qw);
        //最后返回出去的数据 cateFoodsList
        List<CateFoodsVo> res = new ArrayList<>();
        //查看有没有今日推荐的饮品
        LambdaQueryWrapper<Food> recommendFoodQw = new LambdaQueryWrapper<>();
        //根据店铺id,查询店铺今日推荐的饮品
        recommendFoodQw.eq(Food::getShopId, shopId).eq(Food::getIsRecommend, true);
        List<Food> recommendFoods = foodMapper.selectList(recommendFoodQw);
        //如果有推荐的饮品,就生成一个新的分类,----今日推荐--分类
        if (recommendFoods != null && recommendFoods.size() > 0) {
            //封装今日推荐的分类
            CateFoodsVo cateFoodsVo = new CateFoodsVo();
            cateFoodsVo.setCateName("店主今日推荐");
            List<FoodVo> recommendFoodsVoList = new ArrayList<>();
            //把今日推荐的饮品,封装vo
            for (Food recommendFood : recommendFoods) {
                FoodVo foodVo = new FoodVo();
                BeanUtils.copyProperties(recommendFood, foodVo);
                recommendFoodsVoList.add(foodVo);
            }
            //封装完成今日推荐的分类-饮品列表
            cateFoodsVo.setFoods(recommendFoodsVoList);
            res.add(cateFoodsVo);
        }

        //没有今日推荐,按照分类,查询所有的饮品,然后封装cateFoodVo
        for (Category category : categories) {
            CateFoodsVo cateFoodsVo = new CateFoodsVo();
            cateFoodsVo.setCateId(category.getId());
            cateFoodsVo.setCateName(category.getName());
            //根据分类id查询所有food
            LambdaQueryWrapper<Food> foodQw = new LambdaQueryWrapper<>();
            foodQw.eq(Food::getCategoryId, category.getId());
            List<Food> foods = foodMapper.selectList(foodQw);
            List<FoodVo> foodsVoList = new ArrayList<>();
            for (Food food : foods) {
                FoodVo foodVo = new FoodVo();
                BeanUtils.copyProperties(food, foodVo);
                foodsVoList.add(foodVo);
            }
            cateFoodsVo.setFoods(foodsVoList);
            res.add(cateFoodsVo);
        }
        return Result.success(res);
    }
}
