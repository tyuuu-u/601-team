package com.shijiuAPI.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//查询订单 返回的订单列表对象
public class OrderVo {

    //订单id
    private Integer id;

    private String shopName;

    private Integer shopId;

    private Integer status;

    private Boolean commented;

    private String created;

    private String updated;

    private String address;

    private String phone;

    private String name;

    //订单的菜,包括 id name price count imgSrc
    private List<FoodVo> foods;

}
