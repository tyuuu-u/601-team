package com.shijiu.controller;

import com.shijiu.pojo.Order;
import com.shijiu.service.OrderService;
import com.shijiu.utils.UserThreadLocal;
import com.shijiu.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;


    //查询订单列表
    @GetMapping("orderlist")
    public Result getOrderList(){
        return orderService.selectOrderList((Integer) UserThreadLocal.get().get("shopId"));
    }

    //修改订单状态
    @PostMapping("update")
    public Result updateStatus(@RequestBody Order order){
        return orderService.updateStatus(order);
    }
    //逻辑删除订单
    @GetMapping("delete/{id}")
    public Result deleteOrder(@PathVariable("id") Integer id){
        Order order = new Order();
        order.setId(id);
        order.setAdminDelete(true);
        orderService.updateById(order);
        return Result.success("删除订单成功");
    }

    //查看订单详情-根据订单id,查看订单的所有菜品以及数量
    @GetMapping("showfood/{id}")
    public Result showOrderFood(@PathVariable("id") Integer id){

        return orderService.showFood(id);
    }
}
