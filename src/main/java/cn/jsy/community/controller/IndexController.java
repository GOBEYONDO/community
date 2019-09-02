package cn.jsy.community.controller;

import cn.jsy.community.entity.Question;
import cn.jsy.community.entity.User;
import cn.jsy.community.service.QuestionService;
import cn.jsy.community.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @RequestMapping(value ={"/"})
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(value = "page",defaultValue = "1",required = true) Integer page,
                        @RequestParam(value = "pagesize",defaultValue = "10",required = true) Integer pagesize,
                        @RequestParam(value = "search" ,defaultValue = "",required = false) String title){
        List<Question> questions = questionService.findAll(page, pagesize,title);
        PageInfo pageInfo =new PageInfo(questions);
        model.addAttribute("pageInfo",pageInfo);
        List<Integer> pages =new ArrayList<>();
        for (int i=1;i<=pageInfo.getPages();++i)
            pages.add(i);
        model.addAttribute("pages",pages);
        model.addAttribute("questions",questions);
        model.addAttribute("search",title);
        return "index";
    }
    @RequestMapping("exitUser")
    public String exitUser(HttpServletRequest request , HttpServletResponse response){
        Cookie cookie =new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        request.getSession().removeAttribute("user");

        return "redirect:/";
    }
}
