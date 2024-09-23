package com.example.flower.mapper;

import com.example.flower.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    Product findById(@Param("id") Long id);
    List<Product> findAll();
    void insertProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(@Param("id") Long id);
}
