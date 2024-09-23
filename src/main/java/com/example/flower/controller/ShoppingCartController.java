//package com.example.flower.controller;
//
//
//import com.example.flower.entity.ShoppingCartItem;
//import com.example.flower.service.ShoppingCartService;
//import com.example.flower.entity.User;
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/cart")
//public class ShoppingCartController {
//
//    @Autowired
//    private ShoppingCartService shoppingCartService;
//
//    @PostMapping("/add")
//    public Response addItem(HttpServletRequest request, @RequestBody ShoppingCartItem item) {
//        User user = (User) request.getAttribute("user");
//        shoppingCartService.addItem(user.getId(), item);
//        return new Response(true, "添加成功", null);
//    }
//
//    @DeleteMapping("/remove/{productId}")
//    public Response removeItem(HttpServletRequest request, @PathVariable Long productId) {
//        User user = (User) request.getAttribute("user");
//        shoppingCartService.removeItem(user.getId(), productId);
//        return new Response(true, "移除成功", null);
//    }
//
//    @GetMapping
//    public Response getCartItems(HttpServletRequest request) {
//        User user = (User) request.getAttribute("user");
//        List<ShoppingCartItem> items = shoppingCartService.getCartItems(user.getId());
//        return new Response(true, "查询成功", items);
//    }
//
//    @DeleteMapping("/clear")
//    public Response clearCart(HttpServletRequest request) {
//        User user = (User) request.getAttribute("user");
//        shoppingCartService.clearCart(user.getId());
//        return new Response(true, "清空成功", null);
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
