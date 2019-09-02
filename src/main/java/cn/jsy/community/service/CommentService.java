package cn.jsy.community.service;

import cn.jsy.community.entity.Comment;
import cn.jsy.community.model.dto.ResultDTO;

import java.util.List;

public interface CommentService {
    public ResultDTO save(Comment comment);
    public List<Comment> findComents(Integer id ,Integer type,Integer question_id);
}
