package com.example.flower.controller;

import com.example.flower.entity.User;
import com.example.flower.service.UserService;
import com.example.flower.util.JwtUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 用户登录
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.findByOpenid(loginRequest.getOpenid());
        if (user == null) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "用户不存在", null));
        }
//         如果需要密码登录，可在此处验证密码
         if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
             return ResponseEntity.badRequest().body(new ApiResponse(false, "密码错误", null));
         }
        String token = jwtUtil.generateToken(user.getOpenid());
        return ResponseEntity.ok(new ApiResponse(true, "登录成功", token));
    }

    // 用户注册（手动添加用户）
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        // 检查用户是否已存在
        User existingUser = userService.findByOpenid(registerRequest.getOpenid());
        if (existingUser != null) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "用户已存在", null));
        }
        // 创建新用户
        User user = new User();
        user.setOpenid(registerRequest.getOpenid());
        user.setUnionid(registerRequest.getUnionid());
        user.setNickname(registerRequest.getNickname());
        user.setAvatarUrl(registerRequest.getAvatarUrl());
        user.setRole(registerRequest.getRole());
        // 如果需要密码，可以在此处设置并加密
        // user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userService.registerUser(user);
        return ResponseEntity.ok(new ApiResponse(true, "注册成功", null));
    }

    @Data
    static class LoginRequest {
        private String openid;
        private String unionid;
        private String nickname;
        private String avatarUrl;
        private String password;

        public CharSequence getPassword() {

        }
        // private String password; // 如果需要密码登录
    }

    @Data
    static class RegisterRequest {
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
