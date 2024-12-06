package com.shijiu.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shijiu.dao.mapper.UserMapper;
import com.shijiu.params.LoginParam;
import com.shijiu.pojo.Shop;
import com.shijiu.pojo.User;
import com.shijiu.service.LoginService;
import com.shijiu.service.ShopService;
import com.shijiu.service.UserService;
import com.shijiu.utils.JWTUtils;
import com.shijiu.utils.UserThreadLocal;
import com.shijiu.vo.ErrorCode;
import com.shijiu.vo.LoginUserVo;
import com.shijiu.vo.Result;
import com.shijiu.vo.Token;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result login(LoginParam loginParam) {
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMessage());
        }
        User user = userService.loginFindUser(username,password);
        if (user == null){
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMessage());
        }
        Shop shop = new Shop();
        //如果是管理员，店铺id是null，初始化店铺id
        shop.setId(null);
        if(!user.getIsAdmin()) {
            //如果是店主，就查出来店主的店铺id，放入shop中
            shop.setId(shopService.selectShopByUserId(user.getId()).getId());
        }
        //把店主的userid和他的店铺id放入token中，如果是管理员，那shopid为null
        String token = JWTUtils.createToken(user.getId(),shop.getId());
        return Result.success(new Token(token));
    }

    @Override
    public Result loginUserInfo(String token) {
        Map map = UserThreadLocal.get();
        User user = userMapper.selectById((Serializable) map.get("userId"));
        LoginUserVo loginUserVo = new LoginUserVo();
        BeanUtils.copyProperties(user,loginUserVo);
        loginUserVo.setUserId((Integer) map.get("userId"));
        loginUserVo.setShopId((Integer) map.get("shopId"));
        System.out.println("loginUserVo-loginUserInfo-loginServiceImpl"+loginUserVo);
        return Result.success(loginUserVo);
    }

    @Override
    public User checkToken(String token) {
        return null;
    }

    @Override
    public Result logout(String token, HttpServletRequest httpServletRequest) {

        return Result.success(null);
    }

    @Override
    public Result register(LoginParam loginParam) {
        return null;
    }
}
