package cn.jsy.community.service.impl;

import cn.jsy.community.dao.QuestionDao;
import cn.jsy.community.model.dto.UserDTO;
import cn.jsy.community.model.entity.Question;
import cn.jsy.community.model.entity.User;
import cn.jsy.community.model.dto.QuestionDTO;
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
    public void save(QuestionDTO questionDto, User user) {
        questionDto.setGmtCreate(System.currentTimeMillis());
        questionDto.setGmtModified(questionDto.getGmtCreate());
        questionDto.setCreator(user.getId());
        questionDto.setUser(new UserDTO(user));
        questionDto.setComment_count(0);
        questionDto.setLike_count(0);
        questionDto.setView_count(0);
        questionDto.setRandom_id(UUID.randomUUID().toString());

//        ---------------------------------------------

        Question question =new Question();
        question.setRandom_id(questionDto.getRandom_id());
        question.setView_count(questionDto.getView_count());
        question.setId(questionDto.getId());
        question.setLike_count(questionDto.getLike_count());
        question.setComment_count(questionDto.getComment_count());
        question.setCreator(questionDto.getCreator());
        question.setGmtModified(questionDto.getGmtModified());
        question.setGmtCreate(questionDto.getGmtCreate());
        question.setTitle(questionDto.getTitle());
        question.setDescription(questionDto.getDescription());
        questionDao.save(question);
    }

    @Override
    public List<QuestionDTO> findAll(Integer page,Integer pageSize,String title) {
        PageHelper.startPage(page,pageSize);
        title="%"+title+"%";
        return questionDao.findAll(title);
    }

    @Override
    public QuestionDTO findById(Integer id) {
        return questionDao.findById(id);
    }

    @Override
    public void updateById(QuestionDTO questionDTO) {
        Question question =new Question(questionDTO);
        questionDao.updateById(question);
    }

    @Override
    public void updateViewCount(Integer id) {
        questionDao.updateViewCount(id);
    }
}
