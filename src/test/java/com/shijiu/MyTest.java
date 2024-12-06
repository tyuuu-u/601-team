package com.shijiu;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.shijiu.dao.mapper.TestMapper;
import com.shijiu.dao.mapper.UserMapper;
import com.shijiu.pojo.TimeTest;
import com.shijiu.pojo.User;
import com.shijiu.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
public class MyTest {

    @Autowired
    private TestMapper testMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));


        System.out.println(testMapper.selectList(new QueryWrapper<>()));

    }
}
