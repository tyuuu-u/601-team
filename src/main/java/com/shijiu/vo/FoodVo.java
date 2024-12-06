package com.shijiu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodVo {
    Integer id;
    Integer categoryId;
    String name;
    BigDecimal price;
    Boolean isRecommend;
    String imgSrc;
    String description;

    //根据categoryId查出来categoryName
    String categoryName;
}
