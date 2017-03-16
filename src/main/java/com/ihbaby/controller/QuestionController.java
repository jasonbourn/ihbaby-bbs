package com.ihbaby.controller;

import com.ihbaby.config.UserPerm;
import com.ihbaby.form.QuestionQueryForm;
import com.ihbaby.service.QuestionService;
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
@RequestMapping(value = "app/question",produces = MediaType.APPLICATION_JSON_VALUE)
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @RequestMapping(value = "doctors",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @UserPerm(value = false)
    public ApiResult doctorList(int page) throws Exception{
        return questionService.doctorList(page);
    }
    @RequestMapping(value = "questions",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @UserPerm(value = false)
    public ApiResult questionList(QuestionQueryForm form) throws Exception{
        return questionService.questionList(form);
    }
}
