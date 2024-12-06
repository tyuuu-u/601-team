package com.shijiu.handler;

import com.alibaba.fastjson.JSON;
import com.shijiu.pojo.User;
import com.shijiu.service.LoginService;
import com.shijiu.utils.JWTUtils;
import com.shijiu.utils.UserThreadLocal;
import com.shijiu.vo.ErrorCode;
import com.shijiu.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginService loginService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)){
            //handler 可能是 RequestResourceHandler springboot 程序 访问静态资源 默认去classpath下的static目录去查询
            return true;
        }
        String token = request.getHeader("token");

        if (StringUtils.isBlank(token)){
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), "未登录");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        //map里面有登录的时候塞进token的userId和shopId，在loginservice中塞入的
        Map<String, Object> map = JWTUtils.checkToken(token);
        //把map存入threadlocal，方便到时候拿userId和shopId
        UserThreadLocal.put(map);
        //直接放行
        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocal.remove();
    }
}
