package cn.jsy.community.entity;

import lombok.Data;
import java.util.List;

@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer comment_count;
    private Integer view_count;
    private Integer like_count;
    private Integer creator;
    private String random_id;
    private User user;
    private List<Tag> tags;
}
