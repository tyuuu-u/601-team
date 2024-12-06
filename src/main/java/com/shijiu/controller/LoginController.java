package com.shijiu.controller;

import com.shijiu.params.LoginParam;
import com.shijiu.service.LoginService;
import com.shijiu.vo.LoginUserVo;
import com.shijiu.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("index")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    public Result login(@RequestBody LoginParam loginParam){
        return loginService.login(loginParam);
    }

    @GetMapping("userinfo")
    public Result loginUserInfo(String token){
//        System.out.println("userinfo接口接收到的token"+token);
        return loginService.loginUserInfo(token);
    }

    @PostMapping("logout")
    public Result logout(String token,HttpServletRequest httpServletRequest){
        return loginService.logout(token, httpServletRequest);
    }

}
