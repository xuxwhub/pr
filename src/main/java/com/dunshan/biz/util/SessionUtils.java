package com.dunshan.biz.util;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 分布式session工具类
 */
public class SessionUtils {

  /**
   * 获取session
   */
  public static HttpSession getSession() throws RuntimeException {

    final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

    if (Objects.isNull(requestAttributes) || Objects
        .isNull(((ServletRequestAttributes) requestAttributes).getRequest())) {
      throw new RuntimeException("当前线程找不到对应的session信息，请检查是否在Http请求线程中调用此方法。");
    }

    HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
    return request.getSession();
  }

}
