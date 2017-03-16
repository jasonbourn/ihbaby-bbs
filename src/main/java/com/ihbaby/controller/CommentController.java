package com.ihbaby.controller;

import com.ihbaby.config.UserPerm;
import com.ihbaby.entity.Comment;
import com.ihbaby.service.CommentService;
import com.ihbaby.sys.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by qiang on 2017/03/14.
 */
@Controller
@RequestMapping(value = "app/comment",produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {
    @Autowired
    private CommentService commentService;
    @RequestMapping(value = "save",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @UserPerm(value = false)
    public ApiResult saveComment(Comment comment){
        return commentService.saveComment(comment);
    }
}
