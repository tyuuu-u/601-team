package com.shijiuAPI.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shijiuAPI.config.UserThreadLocal;
import com.shijiuAPI.pojo.Order;
import com.shijiuAPI.service.CommentService;
import com.shijiuAPI.service.OrderService;
import com.shijiuAPI.vo.Comment;
import com.shijiuAPI.vo.OrderFoodParam;
import com.shijiuAPI.vo.OrderParam;
import com.shijiuAPI.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CommentService commentService;


    @PostMapping("create")
    public Result createOrder(@RequestBody OrderParam orderParam){
        log.info("sout OrderFoodParam前端传来的{}",orderParam);
        log.info("输出controller里面的threadlocal的openid:{}", UserThreadLocal.get().get("openid"));
        orderService.createOrder(orderParam);
        return Result.success("ok");
    }

    @GetMapping("list")
    public Result orderList(){
        return orderService.orderList();
    }

    @GetMapping("delete/{id}")
    public Result removeById(@PathVariable("id") Integer id){
        Order order = new Order();
        order.setId(id);
        order.setAppDelete(true);
        return  Result.success(orderService.updateById(order));
    }

    //评论为空 则默认评论内容
    @PostMapping("comment/{orderId}")
    public Result comment(@PathVariable("orderId")Integer orderId, @RequestBody Comment comment){
        Order order = new Order();
        order.setId(orderId);
        order.setCommented(true);
        if (comment.getContent()==""){
            comment.setContent("该用户忘记写评论内容了~~");
        }
        //更新订单状态为已评论
        orderService.updateById(order);
        //新增评论
        commentService.save(comment);
        log.info("输出orderID,输出comment对象---{}---{}",orderId,comment);
        return Result.success("收到评论");
    }
}
