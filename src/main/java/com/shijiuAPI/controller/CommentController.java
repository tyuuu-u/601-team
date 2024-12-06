package com.shijiuAPI.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shijiuAPI.service.CommentService;
import com.shijiuAPI.vo.Comment;
import com.shijiuAPI.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("list/{shopId}")
    public Result commentList(@PathVariable("shopId") Integer shopId){
        LambdaQueryWrapper<Comment> qw = new LambdaQueryWrapper<>();
        qw.eq(Comment::getShopId,shopId).orderByDesc(Comment::getCreateDate);
        List<Comment> list = commentService.list(qw);
        return Result.success(list);
    }
}
