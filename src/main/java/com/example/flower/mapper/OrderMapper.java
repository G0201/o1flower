//package com.example.flower.mapper;
//
//
//import com.example.flower.entity.Order;
//import org.apache.ibatis.annotations.*;
//
//import java.util.List;
//
//@Mapper
//public interface OrderMapper {
//    @Insert("INSERT INTO orders (user_id, order_id, product_id, quantity, price, status, address_id, coupon_id, create_time, update_time) VALUES (#{userId}, #{orderId}, #{productId}, #{quantity}, #{price}, #{status}, #{addressId}, #{couponId}, #{createTime}, #{updateTime})")
//    void insertOrder(Order order);
//
//    @Select("SELECT * FROM orders WHERE user_id = #{userId}")
//    List<Order> findOrdersByUserId(Long userId);
//
//    @Update("UPDATE orders SET status = #{status}, update_time = #{updateTime} WHERE order_id = #{orderId}")
//    void updateOrderStatus(@Param("orderId") String orderId, @Param("status") String status, @Param("updateTime") Long updateTime);
//
//    @Select("SELECT * FROM orders WHERE order_id = #{orderId}")
//    Order findOrderByOrderId(String orderId);
//
//    // 其他CRUD方法
//    // 查询超时未支付的订单
//    @Select("SELECT * FROM orders WHERE status = 'UNPAID' AND create_time < #{timeLimit}")
//    List<Order> findUnpaidOrdersBefore(Long timeLimit);
//}
