package com.atguigu.order.service.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.atguigu.order.bean.Order;
import com.atguigu.order.service.OrderService;
import com.atguigu.product.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Slf4j
@Service
public class OrderServiceImpl implements OrderService {



    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    RestTemplate restTemplate;


    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Override
    public Order createOrder(Long productId, Long userId) {


        Product product = getProductFromRemoteWithLoadBalancerAnnotation(productId);
        Order order = new Order();
        order.setId(1L);
        order.setTotalAmount(product.getPrice().multiply(new BigDecimal(product.getNum())));
        order.setUserId(productId);
        order.setNickName("张三");
        order.setAddress("尚硅谷");
        order.setProductList(Arrays.asList(product));
        return order;
    }

    /**
     * 阶段一,仅仅是进行使用远程调用的方法进行调用服务,但是是进行调用指定的服务
     * @param productId
     * @return
     */
    private Product getProductFromRemote(Long productId){
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");


        ServiceInstance instance = instances.get(0);

        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/product/" + productId;
        log.info("远程请求:{}",url);

        Product product = restTemplate.getForObject(url, Product.class);

        return product;
    }


    /**
     * 在之前的远程调用的基础之上使用负载均衡的算法进行优化
     * @param productId
     * @return
     */
    private Product getProductFromRemoteWithLoadBalancer(Long productId){
        ServiceInstance choose = loadBalancerClient.choose("service-product");




        String url = "http://" + choose.getHost() + ":" + choose.getPort() + "/product/" + productId;
        log.info("远程请求:{}",url);

        Product product = restTemplate.getForObject(url, Product.class);

        return product;
    }

    /**
     * 阶段三,基于restTemplate上的注解进行实现负载均衡
     * @param productId
     * @return
     */
    private Product getProductFromRemoteWithLoadBalancerAnnotation(Long productId){

        String url = "http://service-product/product/" + productId;//会进行自动解析服务名的ip以及端口

        //这里的restTemplate是基于负载均衡的注解进行实现的负载均衡
        Product product = restTemplate.getForObject(url, Product.class);

        return product;
    }

}
