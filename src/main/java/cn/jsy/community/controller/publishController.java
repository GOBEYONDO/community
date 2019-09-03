package cn.jsy.community.controller;

import cn.jsy.community.enums.PublishErrorCode;
import cn.jsy.community.model.dto.QuestionDTO;
import cn.jsy.community.model.dto.UserDTO;
import cn.jsy.community.model.entity.User;
import cn.jsy.community.service.QuestionService;
import cn.jsy.community.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;

@Controller
public class publishController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private TagService tagService;
    @PostMapping("setDescription")
    public String setDescription(QuestionDTO question, HttpServletRequest request, @RequestParam(value = "tag",required = true) String tagStr, Model model){
        if (question.getId()==null){
            User user = (User) request.getSession().getAttribute("user");
            if (question.getTitle()==null){
                model.addAttribute("error", PublishErrorCode.TITLE_NOT_NULL);
            }
            if (question.getDescription()==null){
                model.addAttribute("error", PublishErrorCode.DESCRIPTION_NOT_NULL);
            }
            if (question.getTags()==null){
                model.addAttribute("error", PublishErrorCode.TAG_NOT_NULL);
            }
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
