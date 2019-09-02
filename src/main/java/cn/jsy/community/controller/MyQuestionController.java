package cn.jsy.community.controller;

import cn.jsy.community.entity.Comment;
import cn.jsy.community.entity.Question;
import cn.jsy.community.entity.User;
import cn.jsy.community.enums.CustomErrorCodeEnum;
import cn.jsy.community.exception.CustiomException;
import cn.jsy.community.service.CommentService;
import cn.jsy.community.service.QuestionService;
import cn.jsy.community.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MyQuestionController {
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;
    @RequestMapping("myquestions")
    public String findByMyQuestion(@RequestParam(value = "id",required = true) Integer id,
                                   Model model,@RequestParam(value = "page",defaultValue = "1",required = true) Integer page,
                                   @RequestParam(value = "pagesize",defaultValue = "2",required = true) Integer pageSize){
        User user = userService.findById(id,page,pageSize);
        PageInfo pageInfo =new PageInfo(user.getQuestions());
        List<Integer> pages =new ArrayList<>();
        for (int i=1;i<=pageInfo.getPages();++i)
            pages.add(i);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("user",user);
        model.addAttribute("pages",pages);
        return "my-publish-content";
    }
    @RequestMapping("question-main")
    public String findQuestionMain(@RequestParam(value = "id",required = true)Integer id,Model model){
        questionService.updateViewCount(id);
        Question question = questionService.findById(id);
        if (question==null){
            throw new CustiomException(CustomErrorCodeEnum.QUESTION_NOT_FOUND);
        }
        List<Comment> coments = commentService.findComents(id, null,id);
        model.addAttribute("question",question);
        model.addAttribute("comments",coments);
        return "question-main";
    }
    @RequestMapping("backView")
    public ModelAndView backView(Integer id){
        Question question = questionService.findById(id);
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setViewName("publish");
        modelAndView.addObject("question",question);
        return modelAndView;
    }
}
