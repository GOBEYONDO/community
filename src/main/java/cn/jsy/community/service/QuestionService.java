package cn.jsy.community.service;

import cn.jsy.community.entity.Question;
import cn.jsy.community.entity.User;

import java.util.List;


public interface QuestionService {
    public void save(Question question, User user);
    public List<Question> findAll(Integer page,Integer pageSize,String title);
    public Question findById(Integer id);
    public void updateById(Question question);
    public void updateViewCount(Integer id);
}
