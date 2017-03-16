package com.ihbaby.service;

import com.ihbaby.dao.CommentDao;
import com.ihbaby.entity.Comment;
import com.ihbaby.sys.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by qiang on 2017/03/14.
 */
@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;
    public ApiResult saveComment(Comment comment){
        commentDao.save(comment);
        return ApiResult.createSuccess();
    }
}
