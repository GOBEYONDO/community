package cn.jsy.community.controller;

import cn.jsy.community.entity.Question;
import cn.jsy.community.entity.User;
import cn.jsy.community.service.QuestionService;
import cn.jsy.community.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class publishController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private TagService tagService;
    @PostMapping("setDescription")
    public String setDescription(Question question,HttpServletRequest request,@RequestParam(value = "tag",required = true) String tagStr){
        if (question.getId()==null){
            User user = (User) request.getSession().getAttribute("user");
            questionService.save(question,user);
            tagService.saveTagAndQuestionRelation(tagStr,question.getRandom_id());
        }else{
            questionService.updateById(question);
        }
        return "redirect:/";
    }
    @RequestMapping("publish")
    String publish(){
        return "publish";
    }
}
