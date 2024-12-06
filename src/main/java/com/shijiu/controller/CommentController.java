package com.shijiu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shijiu.pojo.Comment;
import com.shijiu.service.CommentService;
import com.shijiu.utils.UserThreadLocal;
import com.shijiu.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
@Slf4j
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("list")
    public Result getCommentList(){
        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Comment::getShopId, UserThreadLocal.get().get("shopId")).orderByDesc(Comment::getCreateDate);
        List<Comment> list = commentService.list(lambdaQueryWrapper);
        log.info("查出来的评论列表{}",list);
        return Result.success(list);
    }

    //添加评论 小程序添加
//    @PostMapping("api/save")
//    public Result saveComment(){
//        return Result.success("ok");
//    }

    //回复评论  店主
    @PostMapping("update")
    public Result updateComment(@RequestBody Comment comment){
        comment.setIsReply(true);
        return Result.success(commentService.updateById(comment));
    }

    //删除评论  管理员删除
//    @GetMapping("admin/remove/{id}")
//    public Result removeComment(@PathVariable("id") Integer id){
//        return Result.success(commentService.removeById(id));
//    }

    //根据id搜索评论,管理员搜索
//    @GetMapping("admin/query/{id}")
//    public Result queryById(@PathVariable("id") Integer id){
//        return Result.success((commentService.getById(id)));
//    }

}
