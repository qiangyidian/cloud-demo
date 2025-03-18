package com.atguigu.order.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component//将该拦截器进行添加到组件中,Springboot是可以进行识别到该组件的
public class XTokenRequestInterceptor implements RequestInterceptor {


    /**
     *
     * @param requestTemplate 请求模版,将本次的详细的信息进行封装到请求的模版中
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        System.out.println("XTokenRequestInterceptor.........");
        requestTemplate.header("X-Token", UUID.randomUUID().toString());
    }
}
