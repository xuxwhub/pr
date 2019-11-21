package com.dunshan.common.log;

/**
 * 日志异常，用于给标记为SystemLog的方法使用，方便记录方法的错误信息及错误码。
 *
 * @author quinn
 */
public class LogAdapterException extends RuntimeException {

  private static final long serialVersionUID = -3180810032876166416L;

  private final int code;

  public LogAdapterException(int code, String message, Throwable cause) {
    super(message, cause);
    this.code = code;
  }

  public LogAdapterException(int code, String message) {
    super(message);
    this.code = code;
  }

  public int getCode() {
    return code;
  }

}
