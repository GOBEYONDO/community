package cn.jsy.community.service;

import cn.jsy.community.entity.Comment;
import cn.jsy.community.entity.Notification;

import java.util.List;

public interface NotificationService {
    public void save(Comment comment);
    public List<Notification> findAll(Integer userId);
}
