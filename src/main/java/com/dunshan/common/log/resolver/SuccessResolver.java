package com.dunshan.common.log.resolver;

import org.springframework.core.annotation.Order;

@Order(1)//最高优先级
public class SuccessResolver implements LogExceptionResolver {

  @Override
  public Error resolve(Throwable t) {
    return t == null ? LogExceptionResolver.Error.SUCCESS : null;
  }

}
