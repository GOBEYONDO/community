package cn.jsy.community.entity;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatar_url;
    private List<Question> questions;

}