package com.shijiuAPI.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shijiuAPI.config.UserThreadLocal;
import com.shijiuAPI.dao.mapper.OrderMapper;
import com.shijiuAPI.pojo.Food;
import com.shijiuAPI.pojo.Order;
import com.shijiuAPI.pojo.OrderFood;
import com.shijiuAPI.service.FoodService;
import com.shijiuAPI.service.OrderFoodService;
import com.shijiuAPI.service.OrderService;
import com.shijiuAPI.service.ShopService;
import com.shijiuAPI.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderFoodService orderFoodService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private FoodService foodService;
    @Override
    public Result createOrder(OrderParam orderParam) {
//         orderFoods=[
//         OrderFoodParam(id=24, shopId=2, count=1),
//         OrderFoodParam(id=3, shopId=1, count=1),
//         OrderFoodParam(id=1, shopId=1, count=2),
//         ])
        Set<Integer> shopIdSet = new HashSet<>();
        for (OrderFoodParam orderFood : orderParam.getOrderFoods()) {
            //把这次提交订单内容的所有店铺id存起来,得到了不重复的所有shopId
            shopIdSet.add(orderFood.getShopId());
        }
        log.info("OrderServiceImpl-createOrder-shopIdSet{}",shopIdSet);
        //为每个店铺生成一个订单
        for (Integer shopId : shopIdSet) {
            Order order = new Order();
            //收货地址、名字、电话、店铺ID、用户的openID
            order.setAddress(orderParam.getAddress());
            order.setName(orderParam.getName());
            order.setPhone(orderParam.getPhone());
            order.setShopId(shopId);
            order.setOpenId(UserThreadLocal.get().get("openid").toString());
            //插入订单返回主键,拿到order的id,再把 orderId,foodId插入到orderFood表中
            orderMapper.insert(order);
            log.info("测试插入生成的orderId主键是否成功:--order.id是{}",order.getId());
            //遍历前端传来的所有菜品,根据shopId找到当前循环店铺的菜
            for (OrderFoodParam orderFood : orderParam.getOrderFoods()) {
                //在订单的所有菜中,找到当前店铺的菜,然后插入orderFood表
                if(orderFood.getShopId()==shopId){
                    OrderFood of = new OrderFood();
                    of.setOrderId(order.getId());
                    of.setFoodId(orderFood.getId());
                    of.setCount(orderFood.getCount());
                    orderFoodService.save(of);
                }
            }
        }
        return Result.success("create orders success");
    }

    @Override
    public Result orderList() {
        //拿到当前线程用户的openid
        String openid = (String) UserThreadLocal.get().get("openid");
        LambdaQueryWrapper<Order> qw = new LambdaQueryWrapper<>();
        //根据openid查询所有订单,查询用户没有删除的订单,根据创建时间排序
        qw.eq(Order::getAppDelete,false)
                .eq(Order::getOpenId,openid)
                .orderByDesc(Order::getCreated);
        List<Order> orderList = orderMapper.selectList(qw);
        //最后返回给前端的订单VoList
        List<OrderVo> orderVoList = new ArrayList<>();
        for (Order order : orderList) {
            OrderVo orderVo = new OrderVo();
            BeanUtils.copyProperties(order,orderVo);
            //根据shopId查询shopName,存入orderVo
            orderVo.setShopName(shopService.getById(order.getShopId()).getShopName());
            LambdaQueryWrapper<OrderFood> orderFoodLambdaQueryWrapper = new LambdaQueryWrapper<>();
            //根据订单id,查询所有订单的菜品id
            orderFoodLambdaQueryWrapper.eq(OrderFood::getOrderId,order.getId());
            //下面的list包含id foodid orderid shopid
            List<OrderFood> orderFoodList = orderFoodService.list(orderFoodLambdaQueryWrapper);
            //最后存入orderVo的订单菜品Vo
            List<FoodVo> foodVos = new ArrayList<>();
            //遍历封装foodVo,设置数量,和其他属性
            for (OrderFood orderFood : orderFoodList) {
                FoodVo foodVo = new FoodVo();
                //设置下单的时候菜品的数量
                foodVo.setCount(orderFood.getCount());
                Food food = foodService.getById(orderFood.getFoodId());
                BeanUtils.copyProperties(food,foodVo);
                foodVos.add(foodVo);

            }
            //把最终的菜品vo存入orderVo里面
            orderVo.setFoods(foodVos);
            //把orderVo存入list中
            orderVoList.add(orderVo);
        }
        return Result.success(orderVoList);
    }

}
