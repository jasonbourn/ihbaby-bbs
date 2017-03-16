package com.ihbaby.service;

import com.ihbaby.dao.PermissionDao;
import com.ihbaby.dao.SysUserDao;
import com.ihbaby.entity.SysPermission;
import com.ihbaby.entity.SysUser;
import com.ihbaby.sys.ApiResult;
import com.ihbaby.utils.DateUtils;
import com.ihbaby.utils.DigestUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by qiang on 2017/02/20.
 */
@Service
public class UserInfoService {
    @Autowired
    private SysUserDao sysUserDao;
    @Value("${jwt.SecretKey}")
    private String jwtSecretKey;
    @Autowired
    private PermissionDao permissionDao;
    public ApiResult login(String userName, String password){
        password = DigestUtils.digest(password);
        SysUser user = sysUserDao.findByNameAndPwd(userName);
        if(user==null){
            return ApiResult.createError("用户不存在！");
        }else if (!user.getPassword().equals(password)){
            return ApiResult.createError("用户名或密码错误！");
        }else {
            List<SysPermission> permissions = permissionDao.getPermissionList(user.getId());
            StringBuilder builder =new StringBuilder();
            for (SysPermission permission : permissions){
                builder.append(permission.getPermissionKey()).append(",");
            }
            Date exp = DateUtils.getDayLater(1);
            String jwtToken = Jwts.builder().setSubject(userName).claim("permission",builder.toString()).setIssuedAt(new Date()).setExpiration(exp)
                    .signWith(SignatureAlgorithm.HS256, jwtSecretKey).compact();
            return ApiResult.createSuccess(jwtToken);
        }

    }


}
