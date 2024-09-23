//package com.example.flower.controller;
//
//
//import lombok.Data;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class ChatController {
//
//    @MessageMapping("/sendMessage")
//    @SendTo("/topic/messages")
//    public ChatMessage sendMessage(ChatMessage message) {
//        // 可以在这里添加业务逻辑，如存储消息记录
//        return message;
//    }
//
//    @Data
//    static class ChatMessage {
//        private String from;
//        private String to;
//        private String content;
//        private Long timestamp;
//    }
//
//}
