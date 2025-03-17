package com.atguigu.order.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


//使用这种方法可以进行实现配置的自动映射,方便进行处理大量的配置,如果配置的属性比较多的话可以使用这种方法
@Component
@ConfigurationProperties(prefix = "order")
@Data
public class OrderProperties {

    String timeout;

    String autoConfirm;
}
