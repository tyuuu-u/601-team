package com.shijiu.controller;

import com.shijiu.pojo.Shop;
import com.shijiu.service.ShopService;
import com.shijiu.utils.UserThreadLocal;
import com.shijiu.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("info")
    public Result getShopInfo(){
        return Result.success(shopService.getById((Integer)UserThreadLocal.get().get("shopId")));
    }

    @PostMapping("update")
    public Result updateShopInfo(@RequestBody Shop shop){
        return Result.success(shopService.updateById(shop));
    }
}
