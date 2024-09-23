package com.example.flower.service;

import com.example.flower.entity.User;

import java.util.List;

public interface UserService {
    User findByOpenid(String openid);
    User findById(Long id);
    void registerUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
    List<User> getAllUsers();
}
