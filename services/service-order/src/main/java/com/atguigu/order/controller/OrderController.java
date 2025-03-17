package com.atguigu.order.controller;


import com.atguigu.order.bean.Order;
import com.atguigu.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {


    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public Order createOrder(@RequestParam("userId") Long userId, @RequestParam("orderId") Long orderId ) {
        Order order = orderService.createOrder(userId,orderId);
        return order;
    }

}
