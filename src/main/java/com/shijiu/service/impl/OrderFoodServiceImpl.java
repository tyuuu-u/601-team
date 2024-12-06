package com.shijiu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shijiu.dao.mapper.OrderFoodMapper;
import com.shijiu.pojo.OrderFood;
import com.shijiu.service.OrderFoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderFoodServiceImpl extends ServiceImpl<OrderFoodMapper, OrderFood> implements OrderFoodService {

    @Autowired
    private OrderFoodMapper orderFoodMapper;
}
