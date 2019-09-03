package cn.jsy.community.dao;

import cn.jsy.community.model.entity.Notification;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NotificationDao {
    @Insert("insert into notification(type,responderId,questionId,view,question_user_id) values(#{type},#{responderId},#{questionId},#{view},#{question_user_id})")
    public void save(Notification notification);
    @Select("select * from notification where question_user_id =#{userId}")
    public List<Notification> findAll(Integer userId);
}
