package com.ihbaby.entity;

/**
 * Created by qiang on 2017/03/14.
 */

public class Answer {
    /**  */
    private Long id;

    /**  */
    private Long questionId;

    /**  */
    private Long userId;

    /** 答案 */
    private String answer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }
}