package com.dunshan.biz.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author xuxinwei
 * @create 2019-11-20
 */
public class CommonControllerInterceptor implements HandlerInterceptor {

  public static final String TEST_FLAG = "dunshan_test";

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    TestFlagHolder.set(request.getHeader(TEST_FLAG));
    return true;
  }

}
