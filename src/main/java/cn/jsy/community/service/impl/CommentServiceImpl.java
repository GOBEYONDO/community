package cn.jsy.community.service.impl;

import cn.jsy.community.dao.CommentDao;
import cn.jsy.community.dao.QuestionDao;
import cn.jsy.community.entity.Comment;
import cn.jsy.community.model.dto.ResultDTO;
import cn.jsy.community.enums.CommentErrorCode;
import cn.jsy.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private QuestionDao questionDao;
    @Transactional
    @Override
    public ResultDTO save(Comment comment) {
        comment.setGmt_create(System.currentTimeMillis());
        comment.setGmt_modified(comment.getGmt_create());
        if (comment.getComment_id() == null) {
            return ResultDTO.errorOf(CommentErrorCode.COMMENT_NOT_LOGIN);
        } else if (comment.getParent_id() == null) {
            return ResultDTO.errorOf(CommentErrorCode.COMMENT_NOT_QUESTION);
        }
        commentDao.save(comment);
        questionDao.updateCommentCount(comment.getParent_id());
        return ResultDTO.errorOf(CommentErrorCode.COMMENT_SUCCESS);
    }
    @Override
    public List<Comment> findComents(Integer id, Integer type,Integer question_id) {
        List<Comment> comments =new ArrayList<>();
        if (type!=null){
            Map<String,Object> map =new HashMap<>();
            map.put("parent_id",id);
            map.put("type",type);
            comments=commentDao.findByParentIdAndType(map);
        }else {
            comments=commentDao.findAll(question_id);
        }
        return comments;
    }
}
