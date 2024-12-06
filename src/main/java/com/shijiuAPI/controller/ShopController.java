package com.shijiuAPI.controller;

import com.shijiuAPI.dao.mapper.SwiperMapper;
import com.shijiuAPI.service.ShopService;
import com.shijiuAPI.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("shop")
public class ShopController {

    @Autowired
    private ShopService shopService;
    @Autowired
    private SwiperMapper swiperMapper;

    @GetMapping("list")
    public Result shopList() {
        return shopService.shopList();
    }

    @GetMapping("swiper")
    public Result swiperList() {
        return Result.success(swiperMapper.selectList(null));
    }

    @GetMapping("foods/{shopId}")
    public Result cateFoodsList(@PathVariable("shopId") Integer shopId) {
        return shopService.cateFoodsList(shopId);
    }

    @GetMapping("info/{shopId}")
    public Result getShopInfo(@PathVariable("shopId") Integer shopId) {

        return Result.success(shopService.getById(shopId));
    }

}
