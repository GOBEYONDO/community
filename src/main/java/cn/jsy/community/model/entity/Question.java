package cn.jsy.community.model.entity;

import cn.jsy.community.model.dto.QuestionDTO;
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
    public Question(){

    }
    public Question(QuestionDTO questionDTO){
        id=questionDTO.getId();
        title=questionDTO.getTitle();
        description=questionDTO.getDescription();
        gmtCreate=questionDTO.getGmtCreate();
        gmtModified=questionDTO.getGmtModified();
        comment_count=questionDTO.getComment_count();
        view_count=questionDTO.getView_count();
        like_count=questionDTO.getLike_count();
        creator=questionDTO.getCreator();
        random_id=questionDTO.getRandom_id();
    }
//    private User user;
//    private List<Tag> tags;
}
