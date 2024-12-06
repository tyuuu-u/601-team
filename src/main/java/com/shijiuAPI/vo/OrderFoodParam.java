package com.shijiuAPI.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//订单参数,里面传过来的菜品列表对象
public class OrderFoodParam {
    //foodId
    private Integer id;

    private Integer shopId;
    //数量
    private Integer count;


}
