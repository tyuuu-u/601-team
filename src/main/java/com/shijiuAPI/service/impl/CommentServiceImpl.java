package com.shijiuAPI.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shijiuAPI.dao.mapper.CommentMapper;
import com.shijiuAPI.service.CommentService;
import com.shijiuAPI.vo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
}
