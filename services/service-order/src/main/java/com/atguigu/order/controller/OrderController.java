package com.atguigu.order.controller;


import com.atguigu.order.bean.Order;
import com.atguigu.order.properties.OrderProperties;
import com.atguigu.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//@RefreshScope//进行自动刷新nacos中的数据,方便进行热部署配置文件
@RestController
public class OrderController {


    @Autowired
    private OrderService orderService;

//    //进行读取nacos中的配置信息
//    @Value("${order.timeout}")
//    String orderTimeout;
//
//    @Value("${order.auto-confirm}")
//    String orderAutoConfirm;


    @Autowired
    OrderProperties orderProperties;

    @GetMapping("/config")
    public String getConfig(){
        return "orderTimeout"+orderProperties.getTimeout()+"orderAutoConfirm"+orderProperties.getAutoConfirm()+"databaseUrl"+orderProperties.getDatabaseUrl();
    }
    @GetMapping("/create")
    public Order createOrder(@RequestParam("productId") Long productId,@RequestParam("userId") Long userId ) {
        Order order = orderService.createOrder(productId,userId);
        return order;
    }


}
