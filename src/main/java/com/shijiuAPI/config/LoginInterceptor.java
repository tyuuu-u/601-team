package com.shijiuAPI.config;

import com.alibaba.fastjson.JSON;
import com.shijiu.vo.ErrorCode;
import com.shijiu.vo.Result;
import com.shijiuAPI.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)){
            Result result = Result.fail(778899, "未登录");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        log.info("LoginInterceptor拦截到的token{}", token);
        //看一下 如果token过期了怎么办
        Map<String, Object> map = JWTUtils.checkToken(token);
        log.info("解析出来的openid==={}", map.get("openid"));
        //把存有openid的map存入threadlocal中
        UserThreadLocal.put(map);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocal.remove();
    }
}
