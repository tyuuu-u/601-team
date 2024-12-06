package com.shijiuAPI.controller;

import cn.hutool.core.util.HashUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.shijiuAPI.utils.JWTUtils;
import com.shijiuAPI.vo.LoginParam;
import com.shijiuAPI.vo.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {

    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.secret}")
    private String secret;
    @Value("${wx.grant_type}")
    private String grant_type;

    @PostMapping("wxLogin")
    public Result login(@RequestBody LoginParam loginParam) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid
                + "&secret=" + secret
                + "&js_code=" + loginParam.getCode()
                + "&grant_type=" + grant_type;
        String s = HttpUtil.get(url);
        JSONObject jsonObject = JSONUtil.parseObj(s);
        System.out.println(jsonObject.getStr("session_key"));
        System.out.println(jsonObject.getStr("openid"));
        String token = JWTUtils.createToken(jsonObject.getStr("openid"));
        return Result.success(token);
    }
}
