package com.atguigu.order.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.common.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;


@ResponseBody
@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler {


    private ObjectMapper objectMapper = new ObjectMapper();//springboot中进行内置的方法,进行处理对象
                                                            //在本次的使用中进行使用的是将对象变为json字符串,然后进行返回
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, String s, BlockException e) throws Exception {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();

        R error = R.error(500,s+"被Sentinel限制了,原因:"+ e.getClass());
        String json  = objectMapper.writeValueAsString(error);
        writer.write(json);
        writer.flush();
        writer.close();
    }
}
