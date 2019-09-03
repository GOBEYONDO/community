package cn.jsy.community.service.impl;

import cn.jsy.community.dao.QuestionDao;
import cn.jsy.community.dao.UserDao;
import cn.jsy.community.model.dto.QuestionDTO;
import cn.jsy.community.model.dto.UserDTO;
import cn.jsy.community.model.entity.User;
import cn.jsy.community.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private QuestionDao questionDao;
    @Override
    public List<UserDTO> findAll() {
        return userDao.findAll();
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User findByToken(String token) {
        return userDao.findByToken(token);
    }

    @Override
    public UserDTO findById(Integer id,Integer page ,Integer pageSize) {
        UserDTO user = userDao.findById(id);
        PageHelper.startPage(page,pageSize);
        List<QuestionDTO> questions = questionDao.findByCreator(user.getId());
        user.setQuestions(questions);
        return user;
    }

    @Override
    public User findByAccountId(String accountId) {
        return userDao.findByAccountId(accountId);
    }
}
