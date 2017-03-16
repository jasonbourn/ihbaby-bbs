package com.ihbaby.controller;

import com.ihbaby.config.UserPerm;
import com.ihbaby.service.UserInfoService;
import com.ihbaby.sys.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by qiang on 2017/02/20.
 */
@Controller
@RequestMapping(value = "sysUser",produces = MediaType.APPLICATION_JSON_VALUE)
public class SysUserController {
    private static final Logger log = LoggerFactory.getLogger(SysUserController.class);
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/getToken", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @UserPerm(value = false)
    public ApiResult login(String userName, String password) {
        if (userName == null || password == null) {
            return ApiResult.createError("请填写用户名和密码");
        }else {
            return userInfoService.login(userName,password);
        }
    }
}
