package cn.jsy.community.service;

import cn.jsy.community.model.dto.CommentDTO;
import cn.jsy.community.model.dto.ResultDTO;

import java.util.List;

public interface CommentService {
    public ResultDTO save(CommentDTO comment);
    public List<CommentDTO> findComents(Integer id ,Integer type);
}
