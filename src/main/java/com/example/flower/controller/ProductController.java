package com.example.flower.controller;

import com.example.flower.entity.Product;
import com.example.flower.service.ProductService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    // 获取所有商品
    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(new ApiResponse(true, "查询成功", products));
    }

    // 获取单个商品
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(new ApiResponse(true, "查询成功", product));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse(false, "商品不存在", null));
        }
    }

    // 添加新商品（管理员权限）
    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductRequest productRequest) {
        Product product = new Product();
        product.setSku(productRequest.getSku());
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setDescription(productRequest.getDescription());
        product.setCategoryId(productRequest.getCategoryId());
        product.setImageUrl(productRequest.getImageUrl());
        productService.addProduct(product);
        return ResponseEntity.ok(new ApiResponse(true, "添加成功", null));
    }

    // 更新商品（管理员权限）
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        Product existingProduct = productService.getProductById(id);
        if (existingProduct == null) {
            return ResponseEntity.status(404).body(new ApiResponse(false, "商品不存在", null));
        }
        existingProduct.setSku(productRequest.getSku());
        existingProduct.setName(productRequest.getName());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setStock(productRequest.getStock());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setCategoryId(productRequest.getCategoryId());
        existingProduct.setImageUrl(productRequest.getImageUrl());
        productService.updateProduct(existingProduct);
        return ResponseEntity.ok(new ApiResponse(true, "更新成功", null));
    }

    // 删除商品（管理员权限）
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Product existingProduct = productService.getProductById(id);
        if (existingProduct == null) {
            return ResponseEntity.status(404).body(new ApiResponse(false, "商品不存在", null));
        }
        productService.deleteProduct(id);
        return ResponseEntity.ok(new ApiResponse(true, "删除成功", null));
    }

    @Data
    static class ProductRequest {
        private String sku;
        private String name;
        private Double price;
        private Integer stock;
        private String description;
        private Long categoryId;
        private String imageUrl;
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
