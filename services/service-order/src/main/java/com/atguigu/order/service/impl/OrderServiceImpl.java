package com.atguigu.order.service.impl;
import java.math.BigDecimal;

import com.atguigu.order.bean.Order;
import com.atguigu.order.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {


    @Override
    public Order createOrder(Long userId, Long orderId) {

        Order order = new Order();
        order.setId(orderId);
        order.setTotalAmount(new BigDecimal("0"));
        order.setUserId(userId);
        order.setNickName("张三");
        order.setAddress("尚硅谷");
        order.setProductList(null);


        return order;
    }
}
