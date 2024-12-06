package com.shijiu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shijiu.pojo.User;

import java.util.List;

public interface UserService extends IService<User> {

    /**
     *  根据账号密码查询用户
     * @param username 登录账号
     * @param password 登录密码
     * @return 返回数据库查询出来的 User
     */
    User loginFindUser(String username, String password);


}
