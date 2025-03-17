package com.atguigu.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OderConfig {


    @LoadBalanced//基于注解实现负载均衡
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
