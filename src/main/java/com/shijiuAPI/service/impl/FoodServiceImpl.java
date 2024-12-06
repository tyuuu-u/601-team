package com.shijiuAPI.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shijiuAPI.dao.mapper.FoodMapper;
import com.shijiuAPI.pojo.Food;
import com.shijiuAPI.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food> implements FoodService {

    @Autowired
    private FoodMapper foodMapper;
}
