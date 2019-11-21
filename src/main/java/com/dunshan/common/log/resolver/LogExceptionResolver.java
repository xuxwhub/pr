package com.dunshan.common.log.resolver;


import com.dunshan.common.log.LoggerConstants;

public interface LogExceptionResolver {

  public static final class Error {

    public static final Error FAILURE = new Error(LoggerConstants.UNKNOWN_ERROR_CODE, "方法执行失败。",
        false);
    public static final Error SUCCESS = new Error(LoggerConstants.SUCCESS_CODE, "方法执行成功。", true);
    //错误码
    private final int code;
    //错误信息
    private final String message;
    //方法是否执行成功
    private final boolean successful;

    public Error(int code, String message, boolean successful) {
      super();
      this.code = code;
      this.message = message;
      this.successful = successful;
    }

    public int getCode() {
      return code;
    }

    public String getMessage() {
      return message;
    }

    public boolean isSuccessful() {
      return successful;
    }

  }

  Error resolve(Throwable t);

}
