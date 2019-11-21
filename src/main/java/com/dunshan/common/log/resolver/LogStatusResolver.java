package com.dunshan.common.log.resolver;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;

@Order(3)
public class LogStatusResolver implements LogExceptionResolver {

  @Override
  public Error resolve(Throwable t) {
    if (t != null) {
      LogStatus logStatus = AnnotationUtils.findAnnotation(t.getClass(), LogStatus.class);
      if (logStatus != null) {
        return new Error(logStatus.value(), logStatus.cause(), false);
      }
    }
    return null;
  }

}
