package cn.jsy.community.service.impl;

import cn.jsy.community.dao.CommentDao;
import cn.jsy.community.dao.NotificationDao;
import cn.jsy.community.dao.QuestionDao;
import cn.jsy.community.dao.UserDao;
import cn.jsy.community.model.entity.Notification;
import cn.jsy.community.model.dto.QuestionDTO;
import cn.jsy.community.model.dto.CommentDTO;
import cn.jsy.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void save(CommentDTO commentDto) {
        Notification notification =new Notification();

        notification.setResponderId(commentDto.getComment_id());

        notification.setType(commentDto.getType());
        notification.setQuestionId(commentDto.getQuestion_id());
        QuestionDTO question = questionDao.findById(commentDto.getQuestion_id());
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
