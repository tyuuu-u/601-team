package com.shijiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shijiu.dao.mapper.UserMapper;
import com.shijiu.pojo.Shop;
import com.shijiu.pojo.User;
import com.shijiu.service.ShopService;
import com.shijiu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ShopService shopService;

    /**
     *  根据账号密码查询用户
     * @param username 登录账号
     * @param password 登录密码
     * @return 返回数据库查询出来的 User
     */
    @Override
    public User loginFindUser(String username, String password) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,username)
                .eq(User::getPassword,password)
                .select(User::getId,User::getUsername,User::getPassword,User::getIsAdmin)
                .last("limit 1 ");
        return userMapper.selectOne(queryWrapper);
    }

}
