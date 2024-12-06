package com.shijiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shijiu.dao.mapper.CateMapper;
import com.shijiu.pojo.Category;
import com.shijiu.service.CateService;
import com.shijiu.utils.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CateServiceImpl extends ServiceImpl<CateMapper,Category> implements CateService {

    @Autowired
    private CateMapper cateMapper;
    @Override
    public Category queryCateById(Integer id) {
        return cateMapper.selectById(id);
    }

    @Override
    public List<Category> getCateList() {
        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Category::getShopId, UserThreadLocal.get().get("shopId"))
                .select(Category::getId,Category::getName);
        List<Category> categoryList = cateMapper.selectList(lambdaQueryWrapper);
        return categoryList;
    }


}
