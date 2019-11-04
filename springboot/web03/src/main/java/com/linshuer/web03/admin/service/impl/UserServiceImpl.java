package com.linshuer.web03.admin.service.impl;

import com.linshuer.web03.admin.entity.User;
import com.linshuer.web03.admin.mapper.UserMapper;
import com.linshuer.web03.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll(){
        return userMapper.findAll();
    }
    @Override
    public List<User> findById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public void create(User user) {
        userMapper.create(user);
    }

    @Override
    public void delete(Long... ids) {
        for (Long id : ids) {
            userMapper.delete(id);
        }
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }


    @Override
    public User findByName(String username) {
        return userMapper.findByName(username);
    }
}
