package com.shijiuAPI.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shijiuAPI.dao.mapper.OrderFoodMapper;
import com.shijiuAPI.pojo.OrderFood;
import com.shijiuAPI.service.OrderFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderFoodServiceImpl extends ServiceImpl<OrderFoodMapper, OrderFood> implements OrderFoodService {

    @Autowired
    private OrderFoodMapper orderFoodMapperl;
}
