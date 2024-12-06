package com.shijiuAPI.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodVo {

    private Integer id;

    private String name;

    private Integer shopId;

    private BigDecimal price;

    private String imgSrc;

    private String description;

    private Integer count = 0;
}
