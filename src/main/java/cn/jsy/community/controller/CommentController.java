package cn.jsy.community.controller;

import cn.jsy.community.entity.Comment;
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
    public Object comment(@RequestBody Comment comment){
        ResultDTO resultDTO = commentService.save(comment);
        notificationService.save(comment);
        return resultDTO;
    }
    @PostMapping("getTowComments")
    @ResponseBody
    public List<Comment> getTowComments(@RequestBody Comment comment, Model model){
        List<Comment> coments = commentService.findComents(comment.getParent_id(), comment.getType(),null);
        return coments;
    }
}
