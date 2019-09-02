package cn.jsy.community.entity;

import lombok.Data;

@Data
public class FileEntity {
    private int success;
    private String message;
    private String url;
    private Integer question_id;
}
