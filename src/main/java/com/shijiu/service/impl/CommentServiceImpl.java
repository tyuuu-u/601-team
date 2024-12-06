package com.shijiu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shijiu.dao.mapper.CommentMapper;
import com.shijiu.pojo.Comment;
import com.shijiu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
}
