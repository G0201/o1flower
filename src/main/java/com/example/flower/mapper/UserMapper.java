package com.example.flower.mapper;

import com.example.flower.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    User findByOpenid(@Param("openid") String openid);
    User findById(@Param("id") Long id);

    List<User> findAllUsers();
    void insertUser(User user);
    void updateUser(User user);
    void deleteUser(@Param("id") Long id);
}
