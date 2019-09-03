package cn.jsy.community.service.impl;

import cn.jsy.community.dao.CommentDao;
import cn.jsy.community.dao.QuestionDao;
import cn.jsy.community.model.entity.Comment;
import cn.jsy.community.model.dto.CommentDTO;
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
    public ResultDTO save(CommentDTO commentDto) {
        commentDto.setGmt_create(System.currentTimeMillis());
        commentDto.setGmt_modified(commentDto.getGmt_create());
        if (commentDto.getComment_id() == null) {
            return ResultDTO.errorOf(CommentErrorCode.COMMENT_NOT_LOGIN);
        } else if (commentDto.getParent_id() == null) {
            return ResultDTO.errorOf(CommentErrorCode.COMMENT_NOT_QUESTION);
        }
    //改动
        Comment comment =new Comment();
        comment.setId(commentDto.getId());
        comment.setGmt_modified(commentDto.getGmt_modified());
        comment.setGmt_create(commentDto.getGmt_create());
        comment.setComment_id(commentDto.getComment_id());
        comment.setContent(commentDto.getContent());
        comment.setLike_count(commentDto.getLike_count());
        comment.setParent_id(commentDto.getParent_id());
        comment.setType(commentDto.getType());
        //end
        commentDao.save(comment);
        questionDao.updateCommentCount(comment.getParent_id());
        return ResultDTO.errorOf(CommentErrorCode.COMMENT_SUCCESS);
    }
    @Override
    public List<CommentDTO> findComents(Integer id, Integer type) {
        List<CommentDTO> comments =new ArrayList<>();
        if (type!=null){
            Map<String,Object> map =new HashMap<>();
            map.put("parent_id",id);
            map.put("type",type);
            comments=commentDao.findByParentIdAndType(map);
        }else {
            List<CommentDTO> twoComments =new ArrayList<>();
            //更改  , 先查询到一级评论 ，再根据一级评论id 查询二级评论
            comments=commentDao.findAll(id);
            for (CommentDTO comment : comments) {
                twoComments.addAll(commentDao.findAll(comment.getId()));
            }
            comments.addAll(twoComments);
        }
        return comments;
    }
}
