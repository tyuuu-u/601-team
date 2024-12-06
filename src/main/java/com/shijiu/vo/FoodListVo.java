package com.shijiu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodListVo {

    //这个店的菜品总共有多少
    private Long total;
    //菜品vo
    private List<FoodVo> foodVoList;
}
