package com.shijiu.controller;

import com.shijiu.pojo.Food;
import com.shijiu.service.FoodService;
import com.shijiu.utils.UserThreadLocal;
import com.shijiu.vo.PageParams;
import com.shijiu.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping("foodlist")
    public Result getFoodList(@RequestBody PageParams pageParams){
        return foodService.queryFoodList(pageParams);
    }

    @PostMapping("update")
    public Result updateFood(@RequestBody Food food){
        System.out.println(food);
        return Result.success(foodService.updateById(food));
    }

    @PostMapping("save")
    public Result saveFood(@RequestBody Food food){
        food.setShopId((Integer) UserThreadLocal.get().get("shopId"));
        return Result.success(foodService.saveOrUpdate(food));
    }

    @GetMapping("remove/{id}")
    public Result removeFood(@PathVariable("id") Integer id){
        return Result.success(foodService.removeById(id));
    }
}
