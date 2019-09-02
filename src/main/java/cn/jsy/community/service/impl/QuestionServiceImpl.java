package cn.jsy.community.service.impl;

import cn.jsy.community.dao.QuestionDao;
import cn.jsy.community.entity.Question;
import cn.jsy.community.entity.User;
import cn.jsy.community.service.QuestionService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionDao questionDao;
    @Override
    public void save(Question question, User user) {
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setCreator(user.getId());
        question.setUser(user);
        question.setComment_count(0);
        question.setLike_count(0);
        question.setView_count(0);
        question.setRandom_id(UUID.randomUUID().toString());
        questionDao.save(question);
    }

    @Override
    public List<Question> findAll(Integer page,Integer pageSize,String title) {
        PageHelper.startPage(page,pageSize);
        title="%"+title+"%";
        return questionDao.findAll(title);
    }

    @Override
    public Question findById(Integer id) {
        return questionDao.findById(id);
    }

    @Override
    public void updateById(Question question) {
        questionDao.updateById(question);
    }

    @Override
    public void updateViewCount(Integer id) {
        questionDao.updateViewCount(id);
    }
}
