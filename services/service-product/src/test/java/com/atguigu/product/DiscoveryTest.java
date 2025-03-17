package com.atguigu.product;

import com.alibaba.cloud.nacos.discovery.NacosDiscoveryClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;


@SpringBootTest
public class DiscoveryTest {


    @Autowired
    DiscoveryClient discoveryClient;//官方通用的服务发现api,所有的服务发现的工具都可以进行调用


    @Autowired
    NacosDiscoveryClient nacosDiscoveryClient;//nacos的服务发现工具,只有使用nacos的服务发现工具才能够进行使用该工具进行服务发现的操作


    //使用不同的工具进行服务发现的操作
    @Test
    void nacosDiscoveryClientTest(){
        for (String service : nacosDiscoveryClient.getServices()) {
            List<ServiceInstance> instances = nacosDiscoveryClient.getInstances(service);
            instances.forEach(instance -> {
                System.out.println(instance);
                System.out.println(instance.getServiceId());
                System.out.println(instance.getHost());
                System.out.println(instance.getPort());
            });
        }
    }
    @Test
    void discoveryClientTest(){
        for (String service : discoveryClient.getServices()) {
            System.out.println(service);
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for(ServiceInstance instance : instances){
                System.out.println(instance);
                System.out.println(instance.getHost());
                System.out.println(instance.getPort());
            }

        }


    }
}
