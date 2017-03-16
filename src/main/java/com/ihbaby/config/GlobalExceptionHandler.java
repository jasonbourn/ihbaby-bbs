package com.ihbaby.config;

import com.ihbaby.controller.SysUserController;
import com.ihbaby.sys.ApiResult;
import com.ihbaby.sys.spring.SignRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by qiang on 2017/03/13.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(value = {Exception.class, ServletException.class})
    @ResponseBody
    public ApiResult<String> jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        log.error("系统错误",e);
        return ApiResult.createError(e.getMessage());
    }

}