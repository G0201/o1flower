package com.example.flower.service.impl;

import com.example.flower.entity.User;
import com.example.flower.mapper.UserMapper;
import com.example.flower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByOpenid(String openid) {
        return userMapper.findByOpenid(openid);
    }

    @Override
    public User findById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public void registerUser(User user) {
        user.setCreateTime(Instant.now().toEpochMilli());
        user.setUpdateTime(Instant.now().toEpochMilli());
        userMapper.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        user.setUpdateTime(Instant.now().toEpochMilli());
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.findAllUsers();
    }
}
