package com.shijiu.controller;

import com.shijiu.pojo.Shop;
import com.shijiu.pojo.User;
import com.shijiu.service.CommentService;
import com.shijiu.service.ShopService;
import com.shijiu.service.UserService;
import com.shijiu.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private ShopService shopService;

    /**
     评论部分
     */
    //管理员展示所有评论
    @GetMapping("commentList")
    public Result commentList() {
        return Result.success(commentService.list());
    }

    //根据评论id查询评论
    @GetMapping("queryComment/{id}")
    public Result queryById(@PathVariable("id") Integer id) {
        return Result.success((commentService.getById(id)));
    }
    //根据评论id删除评论
    @GetMapping("removeComment/{id}")
    public Result removeComment(@PathVariable("id") Integer id) {
        return Result.success(commentService.removeById(id));
    }

    /**
     用户部分
     */
    // 查询用户列表 账号密码
    @GetMapping("userList")
    public Result userList(){
        return Result.success(userService.list());
    }
    //新增用户
    @PostMapping("saveUser")
    public Result saveUser(@RequestBody User user){
        user.setIsAdmin(false);
        return Result.success(userService.save(user));
    }
    //修改用户名密码
    @PostMapping("updateUser")
    public Result updateUser(@RequestBody User user){
        return Result.success(userService.updateById(user));
    }

    /**
     店铺部分
     */

    //查询所有店铺
    @GetMapping("shopList")
    public Result shopList(){
        return Result.success(shopService.list());
    }

    //新增店铺
    @PostMapping("saveShop")
    public Result saveShop(@RequestBody Shop shop){
        return shopService.saveShop(shop);
    }

    //更新店铺
    @PostMapping("updateShop")
    public Result updateShop(@RequestBody Shop shop){
        return Result.success(shopService.updateById(shop));
    }

}
