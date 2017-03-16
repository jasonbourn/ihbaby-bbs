package com.ihbaby.dao;

import com.ihbaby.dao.baseDao.TGJdbcTemplate;
import com.ihbaby.entity.SysPermission;
import com.ihbaby.entity.SysRole;
import com.ihbaby.entity.SysRolePermission;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by qiang on 2017/03/13.
 */
@Repository
public class PermissionDao extends TGJdbcTemplate{
    @Cacheable(value = "permissionByRole",key = "'permissionByRole_'+#roleId")
    public List<SysPermission> getPermissionListByRole(long roleId){
        String sql ="SELECT DISTINCT sys_permission.permission_key FROM sys_role_permission LEFT JOIN sys_permission " +
                "ON sys_role_permission.permission_id = sys_permission.id WHERE role_id = ? AND permission_key IS NOT NULL";
        return getJdbcTemplate().query(sql,new BeanPropertyRowMapper<SysPermission>(SysPermission.class),roleId);
    }
    @Cacheable(value = "userRole",key = "'userRoleList_'+#userId")
    public List<SysRole> getUserRole(long userId){
        String sql = "SELECT sys_role.id, sys_role.role_name, sys_role.hospital_id FROM sys_user_role " +
                "LEFT JOIN sys_role ON sys_user_role.role_id = sys_role.id WHERE sys_user_role.user_id = ?";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<SysRole>(SysRole.class), userId);
    }
    @Cacheable(value = "permissionList",key = "'permissionList_'+#userId")
    public List<SysPermission> getPermissionList(long userId){
        String sql ="SELECT DISTINCT sys_permission.permission_key FROM sys_user_role LEFT JOIN sys_role_permission" +
                " ON sys_role_permission.role_id = sys_user_role.role_id LEFT JOIN sys_permission ON sys_permission.id = sys_role_permission.permission_id WHERE sys_user_role.user_id = ? AND permission_key IS NOT NULL";
        return getJdbcTemplate().query(sql,new BeanPropertyRowMapper<SysPermission>(SysPermission.class),userId);
    }

}
