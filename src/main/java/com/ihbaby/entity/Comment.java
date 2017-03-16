package com.ihbaby.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * Created by qiang on 2017/03/14.
 */
@Document(collection="comment")
public class Comment {
    @Id
    private String _id;
    private String userId;
    private String content;
    private String userName;
    private String toWhoUser;
    private String articleTitle;
    private String userIcon;
    @Indexed
    private Long articleId;
    @Indexed
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToWhoUser() {
        return toWhoUser;
    }

    public void setToWhoUser(String toWhoUser) {
        this.toWhoUser = toWhoUser;
    }


    public Long getArticleId() {
        return articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }
}