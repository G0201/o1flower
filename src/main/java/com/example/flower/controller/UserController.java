package com.example.flower.controller;

import com.example.flower.entity.User;
import com.example.flower.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    // 获取所有用户（管理员权限）
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        // 这里假设有一个方法获取所有用户，需在 UserService 中实现
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(new ApiResponse(true, "查询成功", users));
    }

    // 获取单个用户（管理员权限）
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user != null) {
            return ResponseEntity.ok(new ApiResponse(true, "查询成功", user));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse(false, "用户不存在", null));
        }
    }

    // 更新用户信息（管理员权限）
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        User existingUser = userService.findById(id);
        if (existingUser == null) {
            return ResponseEntity.status(404).body(new ApiResponse(false, "用户不存在", null));
        }
        existingUser.setOpenid(userRequest.getOpenid());
        existingUser.setUnionid(userRequest.getUnionid());
        existingUser.setNickname(userRequest.getNickname());
        existingUser.setAvatarUrl(userRequest.getAvatarUrl());
        existingUser.setRole(userRequest.getRole());
        // 如果需要密码，可以在此处设置并加密
        // existingUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userService.updateUser(existingUser);
        return ResponseEntity.ok(new ApiResponse(true, "更新成功", null));
    }

    // 删除用户（管理员权限）
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        User existingUser = userService.findById(id);
        if (existingUser == null) {
            return ResponseEntity.status(404).body(new ApiResponse(false, "用户不存在", null));
        }
        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponse(true, "删除成功", null));
    }

    @Data
    static class UserRequest {
        private String openid;
        private String unionid;
        private String nickname;
        private String avatarUrl;
        private String role; // CUSTOMER, ADMIN
        // private String password; // 如果需要密码登录
    }

    @Data
    static class ApiResponse {
        private boolean success;
        private String message;
        private Object data;

        public ApiResponse(boolean success, String message, Object data) {
            this.success = success;
            this.message = message;
            this.data = data;
        }
    }
}
