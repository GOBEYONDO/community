package cn.jsy.community.dao;

import cn.jsy.community.model.entity.Tag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagDao {
    @Select("select * from tag")
    public List<Tag> findAll();
    @Insert("insert into tag(tag_name) values(#{tag_name})")
    public void save(Tag tag);
    @Select("select id from tag where tag_name =#{name}")
    public Integer findByName(String name);
    @Insert("insert into question_tag(tag_id,question_random_id) values(#{tag_id},#{question_random_id})")
    public void saveQuestionAndTagsRelation(@Param("tag_id") Integer tag_id, @Param("question_random_id") String question_random_id);
    @Select("select * from tag where id in (select tag_id from question_tag where question_random_id =#{random_id})")
    public List<Tag> findByQuestionRandomId(String random_id);
}
