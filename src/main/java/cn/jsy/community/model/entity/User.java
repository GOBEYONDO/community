package cn.jsy.community.model.entity;

import cn.jsy.community.model.dto.QuestionDTO;
import cn.jsy.community.model.dto.UserDTO;
import cn.jsy.community.model.entity.Question;
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
    //private List<QuestionDTO> questions;

    public User(){

    }
    public User(UserDTO userDTO){
        id =userDTO.getId();
        name =userDTO.getName();
        accountId =userDTO.getAccountId();
        token =userDTO.getToken();
        gmtCreate=userDTO.getGmtCreate();
        gmtModified=userDTO.getGmtModified();
        avatar_url=userDTO.getAvatar_url();
    }

}
