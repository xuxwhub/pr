package com.dunshan.common.log.aspect;


import com.dunshan.biz.util.TestFlagHolder;
import com.dunshan.common.log.LogAdapterException;
import com.dunshan.common.log.UnlogParam;
import com.dunshan.common.log.resolver.LogExceptionResolver;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.annotation.SynthesizingMethodParameter;

/**
 * 方法AOP日志支持基类，支持基于注解的日志打印方式。
 * </p>
 * 思路：为使用spring aop + annotation来实现日志打印的方式提供模版方法。 对实际调用方法进行包装，并且捕获相关异常，推荐使用框架提供的{@link
 * LogAdapterException} 异常对异常进行包装。打印公司统一日志格式。同时在需要的情况下，打印方法参数。
 *
 * @param <T> 日志相关注解类型。
 * @author quinn
 * @see LogAdapterException
 */
public abstract class AbstractLogAspect<T> implements ApplicationContextAware {

  /**
   * 标准日志格式定义。
   */
  private static final String DEFAULT_FORMAT = "{}.{}^|-^|{}^|{}^|{}^|{}^|{}";

  private static final String DEFAULT_STRING_FORMAT = "%s.%s^|-^|%s^|%s^|%s^|%s^|%s";

  private static final String CUSTOM_PARAMS_FORMAT = "[method params:%s]";

  private static final String CUSTOM_RESULT_FORMAT = "[method result:%s]";

  private List<LogExceptionResolver> resolvers = new ArrayList<>();

  /**
   * 包装需要打印日志的方法，并捕获异常信息，在方法结束后打印日志。
   *
   * @param pjp spring aop方法运行时信息。
   * @param annotation 日志相关注解
   * @return 方法调用结果
   * @throws Throwable 抛出原始异常。
   */
  protected Object innerRunWithLog(final ProceedingJoinPoint pjp, T annotation) throws Throwable {
    long begin = System.currentTimeMillis();
    Object result = null;
    Throwable error = null;
    try {
      result = pjp.proceed();
    } catch (Throwable e) {
      error = e;
      throw e instanceof LogAdapterException ? e.getCause() : e;
    } finally {
      afterLog(pjp, annotation, new ExecutionStatus(resolve(error), begin, result, error));
    }
    return result;
  }

  private LogExceptionResolver.Error resolve(Throwable t) {
    Optional<LogExceptionResolver.Error> opt = resolvers.stream()
        .map(resolver -> resolver.resolve(t))
        .filter(e -> e != null).findFirst();
    return opt.isPresent() ? opt.get()
        : (t == null ? LogExceptionResolver.Error.SUCCESS : LogExceptionResolver.Error.FAILURE);
  }

  /**
   * 获取自定义日志内容
   *
   * @param pjp spring aop方法运行时信息。
   * @param annotation 日志相关注解
   * @param status 方法运行时信息，主要包括错误码、错误消息等。
   * @return 自定义日志内容
   */
  protected String getCustomMessage(final ProceedingJoinPoint pjp, T annotation,
      ExecutionStatus status) {
    StringBuilder customMessageBuilder = new StringBuilder(
        preCustomMessage(pjp, annotation, status));
    if (needParams(pjp, annotation, status)) {
      customMessageBuilder.append(String.format(CUSTOM_PARAMS_FORMAT, getArgsString(pjp)));
    }
    if (needResult(pjp, annotation, status)) {
      customMessageBuilder.append(String.format(CUSTOM_RESULT_FORMAT, status.getResult()));
    }
    customMessageBuilder.append(customMessage(pjp, annotation, status));
    return customMessageBuilder.toString();
  }

  /**
   * 获取方法参数后自定义日志内容，需子类实现
   *
   * @param pjp spring aop方法运行时信息。
   * @param annotation 日志相关注解
   * @param status 方法运行时信息，主要包括错误码、错误消息等。
   * @return 方法参数后自定义日志内容
   */
  protected abstract String customMessage(final ProceedingJoinPoint pjp, T annotation,
      ExecutionStatus status);

  protected boolean needResult(final ProceedingJoinPoint pjp, T annotation, ExecutionStatus status) {
    return true;
  }

  /**
   * 是否需要打印方法参数，需子类实现
   *
   * @param pjp spring aop方法运行时信息。
   * @param annotation 日志相关注解
   * @param status 方法运行时信息，主要包括错误码、错误消息等。
   * @return 需要打印方法参数返回是；否则返回false。
   */
  protected abstract boolean needParams(final ProceedingJoinPoint pjp, T annotation,
      ExecutionStatus status);

  /**
   * 获取方法参数前日志内容
   *
   * @param pjp spring aop方法运行时信息。
   * @param annotation 日志相关注解
   * @param status 方法运行时信息，主要包括错误码、错误消息等。
   * @return 方法参数前日志内容
   */
  protected String preCustomMessage(final ProceedingJoinPoint pjp, T annotation,
      ExecutionStatus status) {
    return "";
  }

  // 方法结束后打印日志。
  private void afterLog(final ProceedingJoinPoint pjp, T annotation, ExecutionStatus status) {
    Logger logger = org.slf4j.LoggerFactory.getLogger(pjp.getTarget().getClass());
    Object[] args = new Object[]{pjp.getTarget().getClass().getName(), pjp.getSignature().getName(),
        TestFlagHolder.get4log(),
        status.getSuccess(), status.getCode(), status.getCost(),
        getCustomMessage(pjp, annotation, status)};
    if (status.isSuccessful()) {
      logger.info(DEFAULT_FORMAT, args);
    } else {
      logger.error(String.format(DEFAULT_STRING_FORMAT, args), status.getCause());
    }
  }

  // 获取方法参数部分日志内容。
  private String getArgsString(ProceedingJoinPoint pjp) {
    Method method = ((MethodSignature) pjp.getSignature()).getMethod();
    int paramCount = method.getParameterTypes().length;
    Object[] args = pjp.getArgs();
    int argCount = args.length;
    if (paramCount != argCount) {
      throw new IllegalArgumentException("Number of method parameters " + paramCount
          + " does not match number of argument values " + argCount);
    }
    StringBuilder argsbuilder = new StringBuilder();
    for (int i = 0; i < paramCount; i++) {
      MethodParameter param = new SynthesizingMethodParameter(method, i);
      if (param.getParameterAnnotation(UnlogParam.class) == null) {
        argsbuilder.append(args[i] != null ? args[i].toString() : "");
      }
      argsbuilder.append(";");
    }
    return argsbuilder.toString();
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    Map<String, LogExceptionResolver> matchingBeans = BeanFactoryUtils
        .beansOfTypeIncludingAncestors(applicationContext, LogExceptionResolver.class, true, false);
    if (!matchingBeans.isEmpty()) {
      this.resolvers = new ArrayList<>(matchingBeans.values());
    }
    // We keep resolvers in sorted order.
    AnnotationAwareOrderComparator.sort(this.resolvers);
  }

}
