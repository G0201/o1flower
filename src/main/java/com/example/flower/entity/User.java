package com.example.flower.entity;


import lombok.Data;
import java.io.Serializable;

@Data
public class User implements Serializable {
    private Long id;
    private String openid;
    private String unionid;
    private String nickname;
    private String avatarUrl;
    private String role; // CUSTOMER, DELIVERY_PERSON, ADMIN
    private String password; // 如果需要
    private Long createTime;
    private Long updateTime;
}
