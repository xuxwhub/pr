package com.dunshan.common.exception;

import com.dunshan.common.ErpConstants;
import java.io.Serializable;

/**
 * 异常处理类
 *
 * @author wj
 * @create 2016年11月30日 16:59
 */

public class BusinessInfoException extends RuntimeException implements Serializable {

  private static final long serialVersionUID = -3728032973228613812L;

  /**
   * 具体异常码
   */
  protected int code = ErpConstants.ErrorEnum.ERR_1003.getIndex();

  /**
   * 异常信息
   */
  protected String msg;


  public BusinessInfoException(int code, String msgFormat, Object... args) {
    super(String.format(msgFormat, args));
    this.code = code;
    this.msg = String.format(msgFormat, args);
  }

  public BusinessInfoException() {
    super();
  }


  /**
   * 实例化异常
   */
  public BusinessInfoException newInstance(String msgFormat, Object... args) {
    return new BusinessInfoException(this.code, msgFormat, args);
  }

  public BusinessInfoException(String message, Throwable cause) {
    super(message, cause);
    this.msg = message;
  }

  public BusinessInfoException(Throwable cause) {
    super(cause);
  }

  public BusinessInfoException(String message) {
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