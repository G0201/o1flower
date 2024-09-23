package com.example.flower.service.impl;

import com.example.flower.entity.Product;
import com.example.flower.mapper.ProductMapper;
import com.example.flower.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    @Cacheable(value = "products", key = "#id")
    public Product getProductById(Long id) {
        return productMapper.findById(id);
    }

    @Override
    @Cacheable(value = "allProducts")
    public List<Product> getAllProducts() {
        return productMapper.findAll();
    }

    @Override
    @CacheEvict(value = {"allProducts"}, allEntries = true)
    public void addProduct(Product product) {
        product.setCreateTime(Instant.now().toEpochMilli());
        product.setUpdateTime(Instant.now().toEpochMilli());
        productMapper.insertProduct(product);
    }

    @Override
    @CacheEvict(value = {"products", "allProducts"}, key = "#product.id")
    public void updateProduct(Product product) {
        product.setUpdateTime(Instant.now().toEpochMilli());
        productMapper.updateProduct(product);
    }

    @Override
    @CacheEvict(value = {"products", "allProducts"}, key = "#id")
    public void deleteProduct(Long id) {
        productMapper.deleteProduct(id);
    }
}
