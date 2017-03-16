package com.ihbaby.config;


import com.ihbaby.sys.spring.SignRuntimeException;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.Map.Entry;

public class SpringInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(SpringInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 默认签名
            UserPerm methodAnnotation = handlerMethod.getMethodAnnotation(UserPerm.class);
            if (methodAnnotation == null || methodAnnotation.value()) {
                // 按字母升序排序
                String perm =methodAnnotation.param();
                Claims claims = (Claims) request.getAttribute("claims");
                String permSub =(String) claims.get("permission");
                if (StringUtils.isBlank(permSub)){
                    throw new SignRuntimeException("token失效！");
                }
                String arg[] = permSub.split(",");
                Boolean flag =Arrays.asList(arg).contains(perm);
                if (!flag){
                    throw new SignRuntimeException("您没有权限访问！");
                }
            }
        }
        response.setHeader("Cache-Control", "no-cache,no-store");
        response.setIntHeader("Expires", -1);
        response.setHeader("Pragma", "no-cache");
        return super.preHandle(request, response, handler);
    }
}
