package com.shijiuAPI.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CateFoodsVo {

    private Integer cateId;

    private String cateName;

    //id name price src description
    private List<FoodVo> foods;


}
