package com.dunshan.common.log.resolver;

import com.dunshan.common.log.LogAdapterException;
import org.springframework.core.annotation.Order;


@Order(2)
public class LogAdapterExceptionResolver implements LogExceptionResolver {

  @Override
  public Error resolve(Throwable t) {
    if (t != null && t instanceof LogAdapterException) {
      LogAdapterException exception = (LogAdapterException) t;
      return new Error(exception.getCode(), exception.getMessage(), false);
    }
    return null;
  }

}
