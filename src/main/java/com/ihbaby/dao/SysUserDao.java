package com.ihbaby.dao;

import com.ihbaby.dao.baseDao.TGJdbcTemplate;
import com.ihbaby.entity.SysUser;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by qiang on 2017/03/13.
 */
@Repository
public class SysUserDao extends TGJdbcTemplate{
    public SysUser findByNameAndPwd(String userName){
        try {
            String sql = "select * from sys_user where loginName =?";
            return getJdbcTemplate().queryForObject(sql,new BeanPropertyRowMapper<SysUser>(SysUser.class),userName);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }
}
