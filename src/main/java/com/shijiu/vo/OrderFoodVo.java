package com.shijiu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderFoodVo {

    private Integer id;

    private String imgSrc;

    private String name;

    private BigDecimal price;

    private Integer count;
}
