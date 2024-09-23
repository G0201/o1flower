package com.example.flower.entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class Product implements Serializable {
    private Long id;
    private String sku;
    private String name;
    private Double price;
    private Integer stock;
    private String description;
    private Long categoryId;
    private String imageUrl;
    private Long createTime;
    private Long updateTime;
}
