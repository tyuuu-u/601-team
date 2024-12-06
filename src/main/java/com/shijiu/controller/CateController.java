package com.shijiu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shijiu.dao.mapper.CateMapper;
import com.shijiu.pojo.Category;
import com.shijiu.pojo.Food;
import com.shijiu.service.CateService;
import com.shijiu.utils.UserThreadLocal;
import com.shijiu.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cate")
public class CateController {

    @Autowired
    private CateService cateService;

    @PostMapping("catelist")
    public Result getCateList(){
        return Result.success(cateService.getCateList());
    }

    @PostMapping("save")
    public Result saveCate(@RequestBody Category category){
        System.out.println(category);
        System.out.println(UserThreadLocal.get());
        category.setShopId((Integer) UserThreadLocal.get().get("shopId"));
        return Result.success(cateService.save(category));

    }


    @PostMapping("update")
    public Result updateCate(@RequestBody Category category){
        return Result.success(cateService.updateById(category));
    }

    @GetMapping("remove/{id}")
    public Result removeCate(@PathVariable("id") Integer id){
        return Result.success(cateService.removeById(id));
    }
}
