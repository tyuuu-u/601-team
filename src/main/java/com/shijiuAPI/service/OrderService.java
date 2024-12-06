package com.shijiuAPI.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shijiuAPI.pojo.Order;
import com.shijiuAPI.vo.OrderParam;
import com.shijiuAPI.vo.Result;

public interface OrderService extends IService<Order> {

    Result createOrder(OrderParam orderParam);

    Result orderList();
}
