package cn.jsy.community.controller;

import cn.jsy.community.entity.Tag;
import cn.jsy.community.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TagController {
    @Autowired
    private TagService tagService;
    @RequestMapping("findTags")
    @ResponseBody
    public List<Tag> findAll(){
       return tagService.findAll();
    }
}
