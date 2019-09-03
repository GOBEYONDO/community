package cn.jsy.community.controller;

import cn.jsy.community.model.entity.Comment;
import cn.jsy.community.model.dto.CommentDTO;
import cn.jsy.community.model.dto.ResultDTO;
import cn.jsy.community.service.CommentService;
import cn.jsy.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private NotificationService notificationService;
    @PostMapping("comment")
    @ResponseBody
    public Object comment(@RequestBody CommentDTO comment){
        ResultDTO resultDTO = commentService.save(comment);
        notificationService.save(comment);
        return resultDTO;
    }
    @PostMapping("getTowComments")
    @ResponseBody
    public List<CommentDTO> getTowComments(@RequestBody Comment comment, Model model){
        List<CommentDTO> coments = commentService.findComents(comment.getParent_id(), comment.getType());
        return coments;
    }
}
