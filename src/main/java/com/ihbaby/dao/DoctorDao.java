package com.ihbaby.dao;

import com.ihbaby.dao.baseDao.TGJdbcTemplate;
import com.ihbaby.entity.Doctor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by qiang on 2017/03/14.
 */
@Repository
public class DoctorDao extends TGJdbcTemplate{
    public List<Doctor> findDoctor(int start,int limit){
        String sql ="select * from doctor  limit ?,?";
        return getJdbcTemplate().query(sql,new BeanPropertyRowMapper<Doctor>(Doctor.class),start,limit);
    }
}
