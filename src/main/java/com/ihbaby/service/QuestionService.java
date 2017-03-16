package com.ihbaby.service;

import com.ihbaby.dao.DoctorDao;
import com.ihbaby.dao.QuestionDao;
import com.ihbaby.emus.Const;
import com.ihbaby.entity.Doctor;
import com.ihbaby.entity.Question;
import com.ihbaby.form.QuestionQueryForm;
import com.ihbaby.sys.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by qiang on 2017/03/14.
 */
@Service
public class QuestionService {
    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private QuestionDao questionDao;
    public ApiResult doctorList(int page){
        if (page==0){
            page=1;
        }
        int start =(page-1)* Const.PAGESIZE;
        List<Doctor> doctors =doctorDao.findDoctor(start,Const.PAGESIZE);
        return ApiResult.createSuccess(doctors);
    }

    public ApiResult questionList(QuestionQueryForm form) {
        return ApiResult.createSuccess(questionDao.questionList(form));
    }
}
