package com.ihbaby.dao;

import com.ihbaby.dao.baseDao.TGJdbcTemplate;
import com.ihbaby.emus.Const;
import com.ihbaby.entity.Question;
import com.ihbaby.form.QuestionQueryForm;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by qiang on 2017/03/14.
 */
@Repository
public class QuestionDao extends TGJdbcTemplate {
    public List<Question> questionList(QuestionQueryForm form){
        int start =(form.getPage()-1)* Const.PAGESIZE;
        int end =Const.PAGESIZE;
        String sql ="select question.id, question.title, question.user_id, question.doctor_id, question.create_time, question.type_id, question.`status`, question.view_count, question.answer_count from question where status = ? limit ?,?";
        return getJdbcTemplate().query(sql,new BeanPropertyRowMapper<Question>(Question.class),form.getStatus(),start,end);
    }
}
