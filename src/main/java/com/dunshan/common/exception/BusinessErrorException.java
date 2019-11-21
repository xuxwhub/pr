package com.dunshan.common.exception;

import com.dunshan.common.ErpConstants;
import java.io.Serializable;

/**
 * 异常处理类
 *
 */
public class BusinessErrorException extends RuntimeException implements Serializable {

  private static final long serialVersionUID = -3728032973228613812L;

  /**
   * 具体异常码
   */
  protected int code = ErpConstants.ErrorEnum.ERR_1000.getIndex();

  /**
   * 异常信息
   */
  protected String msg;


  public BusinessErrorException(int code, String msgFormat, Object... args) {
    super(String.format(msgFormat, args));
    this.code = code;
    this.msg = String.format(msgFormat, args);
  }

  public BusinessErrorException() {
    super();
  }


  /**
   * 实例化异常
   */
  public BusinessErrorException newInstance(String msgFormat, Object... args) {
    return new BusinessErrorException(this.code, msgFormat, args);
  }

  public BusinessErrorException(String message, Throwable cause) {
    super(message, cause);
    this.msg = message;
  }

  public BusinessErrorException(Throwable cause) {
    super(cause);
  }

  public BusinessErrorException(String message) {
    super(message);
    this.msg = message;
  }

  public String getMsg() {
    return msg;
  }

  public int getCode() {
    return code;
  }
}