//package com.example.flower.task;
//
//
//import com.example.flower.service.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
///**
// * 定时任务类，用于关闭超过15分钟未支付的订单
// */
//@Component
//public class OrderTask {
//
//    @Autowired
//    private OrderService orderService;
//
//    /**
//     * 每5分钟执行一次
//     */
//    @Scheduled(fixedRate = 300000)
//    public void closeUnpaidOrders() {
//        orderService.closeUnpaidOrders();
//    }
//}
