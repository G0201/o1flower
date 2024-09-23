//package com.example.flower.controller;
//
//
//import com.example.flower.entity.Order;
//import com.example.flower.service.OrderService;
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/payment")
//public class PaymentController {
//
//    @Autowired
//    private OrderService orderService;
//
//    @PostMapping("/initiate")
//    public Response initiatePayment(@RequestBody InitiatePaymentRequest request) {
//        // 根据订单ID生成微信支付参数
//        Order order = orderService.getOrderByOrderId(request.getOrderId());
//        if (order == null) {
//            return new Response(false, "订单不存在", null);
//        }
//        // 调用微信支付API生成支付参数
//        // 这里假设生成的参数为paymentParams
//        String paymentParams = "generated_payment_parameters";
//        return new Response(true, "支付参数生成成功", paymentParams);
//    }
//
//    @PostMapping("/callback")
//    public Response handlePaymentCallback(@RequestBody WeChatPayCallback callback) {
//        // 验证回调签名
//        // 解析回调参数
//        String orderId = callback.getOutTradeNo();
//        String tradeStatus = callback.getTradeStatus();
//
//        if ("SUCCESS".equals(tradeStatus)) {
//            orderService.updateOrderStatus(orderId, "PAID");
//            // 其他处理，如通知用户
//        } else {
//            orderService.updateOrderStatus(orderId, "FAILED");
//            // 其他处理，如通知用户
//        }
//
//        return new Response(true, "回调处理成功", null);
//    }
//
//    @Data
//    static class InitiatePaymentRequest {
//        private String orderId;
//    }
//
//    @Data
//    static class WeChatPayCallback {
//        private String outTradeNo;
//        private String tradeStatus;
//        // 其他字段
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
