package com.dunshan.common.log.resolver;

import java.util.HashMap;
import java.util.Map;
import org.springframework.core.annotation.Order;


@Order(4)
public class LogMappedResolver implements LogExceptionResolver {

  private Map<Class<?>, Error> mapper = new HashMap<>();

  private boolean useDefault = true;

  public void registMap(Class<?> exceptionClass, int code, String msg) {
    this.mapper.put(exceptionClass, new Error(code, msg, false));
  }

  public void setUseDefault(boolean useDefault) {
    this.useDefault = useDefault;
  }

  @Override
  public Error resolve(Throwable t) {
    if (t != null) {
      Error error = this.mapper.get(t.getClass());
      if (error == null && this.useDefault) {
        return Error.FAILURE;
      }
      return error;
    }
    return null;
  }

}
