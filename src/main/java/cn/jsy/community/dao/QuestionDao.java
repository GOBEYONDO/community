package cn.jsy.community.dao;

import cn.jsy.community.entity.Question;
import cn.jsy.community.entity.Tag;
import cn.jsy.community.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
@Mapper
public interface QuestionDao {
    @Insert("insert into question(title,description,gmtCreate,gmtModified,creator,comment_count,view_count,like_count,random_id) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{comment_count},#{view_count},#{like_count},#{random_id})")
    public void save(Question question);
    @Select("select * from question where title like #{titleStr}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "title", property = "title"),
            @Result(column = "description", property = "description"),
            @Result(column = "gmtcreate", property = "gmtCreate"),
            @Result(column = "gmtmodified",property = "gmtModified"),
            @Result(column = "comment_count",property = "comment_count"),
            @Result(column = "view_count",property = "view_count"),
            @Result(column = "like_count",property = "like_count"),
            @Result(column = "random_id",property = "random_id"),
            @Result(column = "creator",property = "user",javaType = User.class,one=@One(select = "cn.jsy.community.dao.UserDao.findById"))
    }
    )
    public List<Question> findAll(String titleStr);
    @Select("select * from question where creator=#{creator}")
    public List<Question> findByCreator(Integer creator);
    @Select("select * from question where id=#{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "title", property = "title"),
            @Result(column = "description", property = "description"),
            @Result(column = "gmtcreate", property = "gmtCreate"),
            @Result(column = "gmtmodified",property = "gmtModified"),
            @Result(column = "comment_count",property = "comment_count"),
            @Result(column = "view_count",property = "view_count"),
            @Result(column = "like_count",property = "like_count"),
            @Result(column = "random_id",property = "random_id"),
            @Result(column = "creator",property = "user",javaType = User.class,one=@One(select = "cn.jsy.community.dao.UserDao.findById")),
            @Result(column = "random_id",property = "tags",javaType = java.util.List.class , many = @Many(select = "cn.jsy.community.dao.TagDao.findByQuestionRandomId"))
    }
    )
    public Question findById(Integer id);

    @Update("update question set title=#{title},description=#{description} where id=#{id}")
    public void  updateById(Question question);
    @Update("update question set view_count = view_count+1 where id =#{id}")
    public void updateViewCount(Integer id);
    @Update("update question set comment_count = comment_count+1 where id =#{id}")
    public void updateCommentCount(Integer id);
}
