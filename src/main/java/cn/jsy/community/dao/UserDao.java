package cn.jsy.community.dao;

import cn.jsy.community.entity.Question;
import cn.jsy.community.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {
    @Select("select * from user")
    public List<User> findAll();
    @Insert("insert into user(name,accountId,token,gmtCreate,gmtModified,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatar_url})")
    public void save(User user);
    @Select("select * from user where token=#{token}")
    public User findByToken(String token);
    @Select("select * from user where id=#{id}")
    public User findById(Integer id);
    @Select("select * from user where accountId =#{accountId}")
    public User findByAccountId(String accountId);
}
