package cn.jsy.community.service;

import cn.jsy.community.model.dto.UserDTO;
import cn.jsy.community.model.entity.User;
import cn.jsy.community.model.dto.QuestionDTO;

import java.util.List;


public interface QuestionService {
    public void save(QuestionDTO questionDto, User user);
    public List<QuestionDTO> findAll(Integer page,Integer pageSize,String title);
    public QuestionDTO findById(Integer id);
    public void updateById(QuestionDTO questionDTO);
    public void updateViewCount(Integer id);
}
