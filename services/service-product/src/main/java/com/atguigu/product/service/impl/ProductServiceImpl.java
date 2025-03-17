package com.atguigu.product.service.impl;
import java.math.BigDecimal;

import com.atguigu.product.bean.Product;
import com.atguigu.product.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Product getByProductId(Long productId) {

        Product product = new Product();
        product.setId(productId);
        product.setPrice(new BigDecimal("99"));
        product.setProductName("苹果手机"+productId);
        product.setNum(2);

        return product;
    }
}
