package com.shijiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shijiu.dao.mapper.ShopMapper;
import com.shijiu.pojo.Shop;
import com.shijiu.pojo.User;
import com.shijiu.service.ShopService;
import com.shijiu.service.UserService;
import com.shijiu.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper,Shop> implements ShopService {

    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private UserService userService;

    /**
     * 根据用户id查询店铺
     * 1。查看这个人是否注册了店铺（一个人只有一家店）
     * 2。登录成功的时候把这个人的店铺查出来返回给前端
     * @param userId 店主的id
     * @return 返回店主的店铺
     */
    @Override
    public Shop selectShopByUserId(Integer userId) {
        LambdaQueryWrapper<Shop> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Shop::getUserId,userId).select(Shop::getId).last("limit 1 ");
        return shopMapper.selectOne(lambdaQueryWrapper);
    }

    @Override
    public Result saveShop(Shop shop) {
        LambdaQueryWrapper<Shop> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Shop::getUserId,shop.getUserId());
        Shop resShop = shopMapper.selectOne(lambdaQueryWrapper);
        if(shop.getUserId() == 1){
            return Result.fail(444,"不能使用管理员账号注册");
        }
        //用收到的userid查询店铺,如果已经存在了,那就返回错误
        if (resShop != null){
            return Result.fail(444,"这个店主编号已经绑定过店铺了,请输入正确的编号");
        }
        //检查userid是否存在
        LambdaQueryWrapper<User> userQw = new LambdaQueryWrapper<>();
        User resUser = userService.getById(shop.getUserId());
        if(resUser == null){
            return Result.fail(777,"这个店主编号不存在,请输入正确的店主编号");
        }
        //userid不存在shop表 && userid存在在user表,说明userid还没注册店铺,直接插入即可
        return Result.success(shopMapper.insert(shop));
    }
}
