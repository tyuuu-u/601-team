package com.shijiuAPI.controller;

import com.shijiuAPI.service.FoodService;
import com.shijiuAPI.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    //food_detail
    @GetMapping("food/{id}")
    public Result getFoodById(@PathVariable("id") Integer foodId){
        return Result.success(foodService.getById(foodId));
    }
}
