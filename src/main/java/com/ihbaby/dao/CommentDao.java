package com.ihbaby.dao;

import com.ihbaby.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by qiang on 2017/03/14.
 */
public interface CommentDao extends MongoRepository<Comment,String>{

}
