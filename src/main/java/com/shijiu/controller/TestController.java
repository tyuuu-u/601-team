package com.shijiu.controller;

import com.alibaba.fastjson.JSON;
import com.shijiu.dao.mapper.TestMapper;
import com.shijiu.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class TestController {

    @Autowired
    private TestMapper testMapper;

    @GetMapping("/test")
    //object 默认是返回json
    public Result testGet(){

        return Result.success(testMapper.selectList(null));
    }
}
