//package com.example.flower.service.impl;
//
//
//import com.example.flower.entity.Order;
//import com.example.flower.mapper.OrderMapper;
//import com.example.flower.mapper.ProductMapper;
//import com.example.flower.service.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.*;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.Instant;
//import java.util.List;
//
//@Service
//public class OrderServiceImpl implements OrderService {
//
//    @Autowired
//    private OrderMapper orderMapper;
//
//    @Autowired
//    private ProductMapper productMapper;
//
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
//
//    private static final String ORDER_TOKEN_PREFIX = "order:token:";
//
//    @Override
//    @Transactional
//    public void createOrder(Long userId, Order order) {
//        // 防重Token验证
//        String tokenKey = ORDER_TOKEN_PREFIX + order.getOrderId();
//        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
//        String token = valueOps.get(tokenKey);
//        if (token == null || !token.equals(order.getOrderId())) {
//            throw new RuntimeException("重复提交订单");
//        }
//
//        // 扣减库存
//        productMapper.updateStock(order.getProductId(), order.getQuantity(), Instant.now().toEpochMilli());
//
//        // 设置订单信息
//        order.setCreateTime(Instant.now().toEpochMilli());
//        order.setUpdateTime(Instant.now().toEpochMilli());
//        order.setStatus("PAID"); // 假设支付成功
//
//        // 插入订单
//        orderMapper.insertOrder(order);
//
//        // 删除购物车数据
//        // 假设有购物车服务接口
//    }
//
//    @Override
//    public List<Order> getOrdersByUserId(Long userId) {
//        return orderMapper.findOrdersByUserId(userId);
//    }
//
//    @Override
//    @Transactional
//    public void updateOrderStatus(String orderId, String status) {
//        orderMapper.updateOrderStatus(orderId, status, Instant.now().toEpochMilli());
//    }
//
//    @Override
//    public Order getOrderByOrderId(String orderId) {
//        return orderMapper.findOrderByOrderId(orderId);
//    }
//
//    @Override
//    public void closeUnpaidOrders() {
//        // 定时任务逻辑，查询未支付的订单并关闭
//        // 示例：假设查询到需要关闭的订单列表
//        List<Order> ordersToClose = orderMapper.findUnpaidOrdersBefore(Instant.now().minusSeconds(900).toEpochMilli());
//        for (Order order : ordersToClose) {
//            updateOrderStatus(order.getOrderId(), "CLOSED");
//        }
//    }
//}
