package cn.jsy.community.service.impl;

import cn.jsy.community.dao.CommentDao;
import cn.jsy.community.dao.NotificationDao;
import cn.jsy.community.dao.QuestionDao;
import cn.jsy.community.dao.UserDao;
import cn.jsy.community.entity.Comment;
import cn.jsy.community.entity.Notification;
import cn.jsy.community.entity.Question;
import cn.jsy.community.entity.User;
import cn.jsy.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private NotificationDao notificationDao;
    @Transactional
    @Override
    public void save(Comment comment) {
        Notification notification =new Notification();

        notification.setResponderId(comment.getComment_id());

        notification.setType(comment.getType());
        notification.setQuestionId(comment.getQuestion_id());
        Question question = questionDao.findById(comment.getQuestion_id());
        notification.setQuestion_user_id(question.getUser().getId());
        notification.setView(0);
        notificationDao.save(notification);
    }

    public List<Notification> findAll(Integer userId){
        List<Notification> notifications = notificationDao.findAll(userId);
        for (Notification notification : notifications) {
            notification.setUsername(userDao.findById(notification.getQuestion_user_id()).getName());
            notification.setQuestionname(questionDao.findById(notification.getQuestionId()).getTitle());
        }
        return notifications;
    }
}
