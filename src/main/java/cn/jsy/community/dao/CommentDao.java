package cn.jsy.community.dao;

import cn.jsy.community.model.entity.Comment;
import cn.jsy.community.model.entity.User;
import cn.jsy.community.model.dto.CommentDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CommentDao {
    @Insert("insert into comment(parent_id,type,comment_id,gmt_create,gmt_modified,like_count,content) values(#{parent_id},#{type},#{comment_id},#{gmt_create},#{gmt_modified},#{like_count},#{content})")
    public void save(Comment comment);
    @Select("select * from comment where parent_id =#{parent_id} and type =#{type} order by gmt_create desc")
    @Results({
            @Result(id = true ,column = "id",property = "id"),
            @Result(column = "type",property = "type"),
            @Result(column = "parent_id",property = "parent_id"),
            @Result(column = "gmt_create", property = "gmt_create"),
            @Result(column = "gmt_modified", property = "gmt_modified"),
            @Result(column = "like_count", property = "like_count"),
            @Result(column = "content", property = "content"),
            @Result(column = "comment_id",property = "user", javaType = User.class,one = @One(select = "cn.jsy.community.dao.UserDao.findById"))
    })
    public List<CommentDTO> findByParentIdAndType(Map<String,Object> map);

    @Select("select * from comment where parent_id = #{id} order by gmt_create desc")
    @Results({
            @Result(id = true ,column = "id",property = "id"),
            @Result(column = "type",property = "type"),
            @Result(column = "parent_id",property = "parent_id"),
            @Result(column = "gmt_create", property = "gmt_create"),
            @Result(column = "gmt_modified", property = "gmt_modified"),
            @Result(column = "like_count", property = "like_count"),
            @Result(column = "content", property = "content"),
            @Result(column = "comment_id",property = "user", javaType = User.class,one = @One(select = "cn.jsy.community.dao.UserDao.findById"))
    })
    public List<CommentDTO> findAll(Integer id);
    @Select("select * from comment where id =#{id}")
    public Comment findById(Integer id);
}