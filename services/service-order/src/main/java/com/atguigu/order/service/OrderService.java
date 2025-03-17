package com.atguigu.order.service;

import com.atguigu.order.bean.Order;

public interface OrderService {
    Order createOrder(Long userId, Long orderId);
}
