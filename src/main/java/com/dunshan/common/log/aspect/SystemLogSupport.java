package com.dunshan.common.log.aspect;

import com.dunshan.common.log.SystemLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 系统日志切面实现类。
 *
 * @author quinn
 * @see SystemLog
 */
@Aspect
@Component
public class SystemLogSupport extends AbstractLogAspect<SystemLog> {

  /**
   * 使用SystemLog注解作为切点
   */
  @Pointcut("@annotation(com.dunshan.common.log.SystemLog)")
  public void logPointcut() {
  }

  /**
   * 在调用带有SystemLog注解的方法时打印日志。 提供统计方法调用耗时，错误码，错误信息，异常等。
   *
   * @param pjp 方法运行时的调用信息
   * @param systemLog 方法注解，用于获取用户配置的日志打印信息（如：是否打印参数等）。
   * @return 原方法调用结果
   * @throws Throwable 抛出异常
   */
  @Around("logPointcut() && @annotation(systemLog)")
  public Object log(final ProceedingJoinPoint pjp, SystemLog systemLog) throws Throwable {
    return innerRunWithLog(pjp, systemLog);
  }

  @Override
  protected String customMessage(ProceedingJoinPoint pjp, SystemLog annotation,
      ExecutionStatus status) {
    return status.isSuccessful() ? annotation.value() : status.getMessage();
  }

  @Override
  protected boolean needParams(ProceedingJoinPoint pjp, SystemLog annotation,
      ExecutionStatus status) {
    return annotation.enableArgsLog() ? (annotation.argsOnlyOnError() ? (!status.isSuccessful())
        : true) : false;
  }

  @Override
  protected boolean needResult(ProceedingJoinPoint pjp, SystemLog annotation,
      ExecutionStatus status) {
    return annotation.enableResultLog();
  }
}
