package com.shijiu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shijiu.pojo.Shop;
import com.shijiu.vo.Result;

public interface ShopService extends IService<Shop> {

    /**
     * 根据用户id查询店铺id
     * 1。查看这个人是否注册了店铺（一个人只有一家店）
     * 2。登录成功的时候把这个人的店铺id查出来返回给前端
     * @param userId 店主的id
     * @return 返回店主的店铺id
     */
    Shop selectShopByUserId(Integer userId);

    Result saveShop(Shop shop);
}
