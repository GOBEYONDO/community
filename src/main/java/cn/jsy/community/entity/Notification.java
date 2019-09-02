package cn.jsy.community.entity;

import lombok.Data;

import java.util.List;

@Data
public class Notification {
    private  Integer id;
    private  Integer type;
    private  Integer responderId;
    private Integer  questionId;
    private Integer view;
    private Integer question_user_id;
    private String username;
    private String questionname;
}
