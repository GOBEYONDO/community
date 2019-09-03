package cn.jsy.community.service;

import cn.jsy.community.model.entity.Notification;
import cn.jsy.community.model.dto.CommentDTO;

import java.util.List;

public interface NotificationService {
    public void save(CommentDTO commentDto);
    public List<Notification> findAll(Integer userId);
}
