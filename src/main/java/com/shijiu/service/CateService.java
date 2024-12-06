package com.shijiu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shijiu.pojo.Category;

import java.util.List;

public interface CateService extends IService<Category> {

    Category queryCateById(Integer id);

    List<Category> getCateList();

}
