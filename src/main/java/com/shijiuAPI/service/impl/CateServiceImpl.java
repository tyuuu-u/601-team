package com.shijiuAPI.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shijiuAPI.dao.mapper.CateMapper;
import com.shijiuAPI.pojo.Category;
import com.shijiuAPI.service.CateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CateServiceImpl extends ServiceImpl<CateMapper, Category> implements CateService {

    @Autowired
    private CateMapper cateMapper;
}
