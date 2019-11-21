package com.dunshan.common.log.aspect;

import com.dunshan.common.log.LoggerConstants;
import com.dunshan.common.log.resolver.LogExceptionResolver;
import com.dunshan.common.log.resolver.LogExceptionResolver.Error;


/**
 * 被调用方法运行完成时信息，主要用于收集方法是否成功、错误码、错误信息、错误原因等， 与spring aop的{@link ProceedingJoinPoint}互为补充。
 *
 * @author quinn
 */
final class ExecutionStatus {

  private final Error error;
  //标准日志格式中方法是否成功标志
  private final String success;
  //方法耗时
  private final long cost;
  //错误原因
  private final Throwable cause;

  private final Object result;

  public ExecutionStatus(LogExceptionResolver.Error error, long begin, Object result, Throwable cause) {
    super();
    this.error = error;
    this.success = error.isSuccessful() ? LoggerConstants.SUCCESS : LoggerConstants.FAILURE;
    this.cost = System.currentTimeMillis() - begin;
    this.cause = cause;
    this.result = result;
  }

  public boolean isSuccessful() {
    return error.isSuccessful();
  }

  public String getSuccess() {
    return success;
  }

  public int getCode() {
    return error.getCode();
  }

  public long getCost() {
    return cost;
  }

  public String getMessage() {
    return error.getMessage();
  }

  public Throwable getCause() {
    return cause;
  }

  public Object getResult() {
    return result;
  }
}
