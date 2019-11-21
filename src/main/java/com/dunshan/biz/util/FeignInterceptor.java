package com.dunshan.biz.util;


import static com.dunshan.biz.util.CommonControllerInterceptor.TEST_FLAG;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeignInterceptor implements RequestInterceptor{
 
    public void apply(RequestTemplate requestTemplate){
        requestTemplate.header(TEST_FLAG, TestFlagHolder.get());
    }
}