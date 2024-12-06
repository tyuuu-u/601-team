package com.shijiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shijiu.dao.mapper.OrderMapper;
import com.shijiu.pojo.Food;
import com.shijiu.pojo.Order;
import com.shijiu.pojo.OrderFood;
import com.shijiu.service.FoodService;
import com.shijiu.service.OrderFoodService;
import com.shijiu.service.OrderService;
import com.shijiu.vo.OrderFoodVo;
import com.shijiu.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderFoodService orderFoodService;
    @Autowired
    private FoodService foodService;
    @Override
    public Result selectOrderList(Integer shopId) {
        LambdaQueryWrapper<Order> qw = new LambdaQueryWrapper<>();
        qw.select(Order::getId,
                    Order::getOpenId,
                    Order::getName,
                    Order::getAddress,
                    Order::getPhone,
                    Order::getStatus,
                    Order::getCreated,
                    Order::getUpdated)
                .eq(Order::getShopId,shopId)
                .eq(Order::getAdminDelete,false)
                .orderByDesc(Order::getCreated);
        List<Order> orders = orderMapper.selectList(qw);
        log.info("OrderServiceImpl sout orders {}",orders);
        return Result.success(orders);
    }

    public Result updateStatus(Order order){
        orderMapper.updateById(order);
        return Result.success("ok");
    }

    @Override
    public Result showFood(Integer id) {
        LambdaQueryWrapper<OrderFood> qw = new LambdaQueryWrapper<>();
        qw.eq(OrderFood::getOrderId,id);
        //根据订单id在orderfood表中查询出对应的所有饮品包括(foodId,foodCount)
        List<OrderFood> orderFoodList = orderFoodService.list(qw);
        //最终展示的饮品列表
        List<OrderFoodVo> result = new ArrayList<>();
        for (OrderFood orderFood : orderFoodList) {
            //在food表中查询出来完整的food对象
            Food food = foodService.getById(orderFood.getFoodId());
            OrderFoodVo orderFoodVo = new OrderFoodVo();
            //拷贝基本属性
            BeanUtils.copyProperties(food,orderFoodVo);
            //设置饮品数量
            orderFoodVo.setCount(orderFood.getCount());
            result.add(orderFoodVo);
        }
        return Result.success(result);
    }
}
