package com.shijiu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shijiu.pojo.Food;
import com.shijiu.vo.PageParams;
import com.shijiu.vo.Result;

import java.util.List;

public interface FoodService extends IService<Food> {

    Result queryFoodList(PageParams pageParams);
}
