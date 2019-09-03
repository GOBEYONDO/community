package cn.jsy.community.service;

import cn.jsy.community.model.dto.UserDTO;
import cn.jsy.community.model.entity.User;

import java.util.List;

public interface UserService {
    public List<UserDTO> findAll();
    public void save(User user);
    public User findByToken(String token);
    public UserDTO findById(Integer id, Integer page, Integer pageSize);
    public User findByAccountId(String accountId);
}
