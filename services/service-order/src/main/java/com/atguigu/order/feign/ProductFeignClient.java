package com.atguigu.order.feign;


import com.atguigu.order.feign.fallback.ProductFeignClientFallback;
import com.atguigu.product.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//@RequestMapping("/api/product")
@FeignClient(value="service-product",fallback = ProductFeignClientFallback.class)         //feign客户端
public interface ProductFeignClient {


    //mvc注解的两套使用逻辑
    //1标注在controller上的时候是接收这样的注解
    //2标注在FeignClient上是发送这样的请求
    @GetMapping("/api/product/product/{id}")
    Product getProductById(@PathVariable("id") Long id);
}
