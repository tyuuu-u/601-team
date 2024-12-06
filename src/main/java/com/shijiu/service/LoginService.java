package com.shijiu.service;

import com.shijiu.params.LoginParam;
import com.shijiu.pojo.User;
import com.shijiu.vo.Result;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

    /**
     * 登录功能
     * @param loginParam
     * @return
     */
    Result login(LoginParam loginParam);

    /**
     * 登陆成功后获取用户信息
     * @param token 需要携带token
     * @return
     */
    Result loginUserInfo(String token);

    User checkToken(String token);

    /**
     * 退出登录
     * @param token
     * @return
     */
    Result logout(String token, HttpServletRequest httpServletRequest);

    /**
     * 注册
     * @param loginParam
     * @return
     */
    Result register(LoginParam loginParam);
}
