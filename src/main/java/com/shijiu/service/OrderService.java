package com.shijiu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shijiu.pojo.Order;
import com.shijiu.vo.Result;


public interface OrderService extends IService<Order> {

    //根据店铺id查询店铺所有订单
    Result selectOrderList(Integer id);

    //更新订单状态
    Result updateStatus(Order order);

    //展示订单的所有事物
    Result showFood(Integer id);
}
