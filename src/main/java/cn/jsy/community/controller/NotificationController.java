package cn.jsy.community.controller;

import cn.jsy.community.model.entity.Notification;
import cn.jsy.community.service.NotificationService;
import cn.jsy.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserService userService;
    @RequestMapping("notification")
    public String notification(Integer userId, Model model){
        List<Notification> notifications = notificationService.findAll(userId);
        List<String> userNames =new ArrayList<>();
        model.addAttribute("notifications",notifications);
        return "notification";
    }
}
