//package com.example.flower.service.impl;
//
//
//import com.example.flower.entity.ShoppingCartItem;
//import com.example.flower.service.ShoppingCartService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.*;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Service
//public class ShoppingCartServiceImpl implements ShoppingCartService {
//
//    private static final String CART_KEY_PREFIX = "cart:";
//
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
//
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    @Override
//    public void addItem(Long userId, ShoppingCartItem item) {
//        String key = CART_KEY_PREFIX + userId;
//        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
//        try {
//            String itemJson = objectMapper.writeValueAsString(item);
//            hashOps.put(key, String.valueOf(item.getProductId()), itemJson);
//        } catch (Exception e) {
//            // 处理序列化异常
//        }
//    }
//
//    @Override
//    public void removeItem(Long userId, Long productId) {
//        String key = CART_KEY_PREFIX + userId;
//        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
//        hashOps.delete(key, String.valueOf(productId));
//    }
//
//    @Override
//    public List<ShoppingCartItem> getCartItems(Long userId) {
//        String key = CART_KEY_PREFIX + userId;
//        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
//        Map<String, String> entries = hashOps.entries(key);
//        List<ShoppingCartItem> items = new ArrayList<>();
//        entries.values().forEach(itemJson -> {
//            try {
//                ShoppingCartItem item = objectMapper.readValue(itemJson, ShoppingCartItem.class);
//                items.add(item);
//            } catch (Exception e) {
//                // 处理反序列化异常
//            }
//        });
//        return items;
//    }
//
//    @Override
//    public void clearCart(Long userId) {
//        String key = CART_KEY_PREFIX + userId;
//        redisTemplate.delete(key);
//    }
//}
