//package com.example.flower.controller;
//
//
//import com.example.flower.entity.Order;
//import com.example.flower.entity.User;
//import com.example.flower.service.OrderService;
//import com.example.flower.service.ShoppingCartService;
//import com.example.flower.entity.ShoppingCartItem;
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/api/orders")
//public class OrderController {
//
//    @Autowired
//    private OrderService orderService;
//
//    @Autowired
//    private ShoppingCartService shoppingCartService;
//
//    @PostMapping("/create")
//    public Response createOrder(HttpServletRequest request, @RequestBody CreateOrderRequest createOrderRequest) {
//        User user = (User) request.getAttribute("user");
//
//        // 从购物车获取商品
//        List<ShoppingCartItem> items = shoppingCartService.getCartItems(user.getId());
//        if (items.isEmpty()) {
//            return new Response(false, "购物车为空", null);
//        }
//
//        // 创建订单
//        Order order = new Order();
//        order.setOrderId(UUID.randomUUID().toString());
//        order.setUserId(user.getId());
//        order.setProductId(items.get(0).getProductId());
//        order.setQuantity(items.get(0).getQuantity());
//        order.setPrice(items.get(0).getPrice() * items.get(0).getQuantity());
//        order.setStatus("PENDING");
//        order.setAddressId(createOrderRequest.getAddressId());
//        order.setCouponId(createOrderRequest.getCouponId());
//
//        // 生成防重Token
//        String token = order.getOrderId();
//        String tokenKey = "order:token:" + token;
//        // 将Token存入Redis，5分钟后过期
//        // 这里假设有Redis操作
//        // redisTemplate.opsForValue().set(tokenKey, token, 5, TimeUnit.MINUTES);
//
//        // 创建订单
//        orderService.createOrder(user.getId(), order);
//
//        // 清空购物车
//        shoppingCartService.clearCart(user.getId());
//
//        return new Response(true, "订单创建成功", order.getOrderId());
//    }
//
//    @GetMapping
//    public Response getUserOrders(HttpServletRequest request) {
//        User user = (User) request.getAttribute("user");
//        List<Order> orders = orderService.getOrdersByUserId(user.getId());
//        return new Response(true, "查询成功", orders);
//    }
//
//    @Data
//    static class CreateOrderRequest {
//        private Long addressId;
//        private Long couponId;
//    }
//
//    @Data
//    static class Response {
//        private boolean success;
//        private String message;
//        private Object data;
//
//        public Response(boolean success, String message, Object data) {
//            this.success = success;
//            this.message = message;
//            this.data = data;
//        }
//    }
//}
