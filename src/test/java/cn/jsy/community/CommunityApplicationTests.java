package cn.jsy.community;

import cn.jsy.community.dao.QuestionDao;
import cn.jsy.community.dao.TagDao;
import cn.jsy.community.dao.UserDao;
import cn.jsy.community.entity.Comment;
import cn.jsy.community.entity.Question;
import cn.jsy.community.entity.Tag;
import cn.jsy.community.entity.User;
import cn.jsy.community.service.CommentService;
import cn.jsy.community.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommunityApplicationTests {
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.url}")
    private  String url;
    @Autowired
   private UserDao userDao;
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private CommentService commentService;
    @Autowired
    private TagDao tagDao;
    @Test
    public void contextLoads() {
        Question byId = questionDao.findById(548);
        System.out.println(byId.getTags());
    }
}
