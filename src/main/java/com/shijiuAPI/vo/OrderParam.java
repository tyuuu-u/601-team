package com.shijiuAPI.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderParam {
    //前端传来的订单参数 包括:用户名字,电话,地址,{foodid,shopid,count}

    private String name;

    private String address;

    private String phone;

    private List<OrderFoodParam> orderFoods;
}
