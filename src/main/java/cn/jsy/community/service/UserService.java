package cn.jsy.community.service;

import cn.jsy.community.entity.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();
    public void save(User user);
    public User findByToken(String token);
    public User findById(Integer id,Integer page,Integer pageSize);
    public User findByAccountId(String accountId);
}
