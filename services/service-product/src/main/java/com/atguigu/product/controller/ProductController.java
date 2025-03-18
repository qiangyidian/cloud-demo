package com.atguigu.product.controller;


import com.atguigu.product.bean.Product;
import com.atguigu.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;
import java.util.concurrent.TimeUnit;

@RestController
public class ProductController {


    @Autowired
    private ProductService productService;


    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") Long productId ,HttpServletRequest request) throws InterruptedException {
        Product product = productService.getByProductId(productId);
        System.out.println("hello");
        //进行获取到order中的拦截器进行添加的请求头,打印出来
        System.out.println(request.getHeader("X-Token"));
        //进行设置休眠的时间,查看是否会进行重试
//        Thread.sleep(10000);
        return product;
    }
}
