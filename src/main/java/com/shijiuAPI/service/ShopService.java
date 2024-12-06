package com.shijiuAPI.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shijiuAPI.pojo.Shop;
import com.shijiuAPI.vo.Result;

public interface ShopService extends IService<Shop> {

    Result shopList();

    Result cateFoodsList(Integer shopId);
}
