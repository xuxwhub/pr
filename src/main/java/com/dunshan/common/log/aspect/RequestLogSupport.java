package com.dunshan.common.log.aspect;

import java.util.Objects;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 请求日志AOP，通过AOP的方式，记录非get请求的url及参数。
 * </p>
 *
 * @author quinn
 */
@Aspect
@Component
public class RequestLogSupport extends AbstractLogAspect<Object> {

  private static final int RESULT_MAX_LENTH = 300;

  private static final String URL_LOG_FORMAT = "[请求url:%s]";

  // RequestMapping 切点配置
  @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) && !@annotation(com.dunshan.common.log.Unlog)")
  public void requestMappingPointcut() {
  }

  @Around("requestMappingPointcut() && @annotation(requestMapping)")
  public Object runWithLog(final ProceedingJoinPoint pjp, RequestMapping requestMapping)
      throws Throwable {
    return innerRunWithLog(pjp, requestMapping);
  }

  // GetMapping 切点配置
  @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping) && !@annotation(com.dunshan.common.log.Unlog)")
  public void getMappingPointcut() {
  }

  @Around("getMappingPointcut() && @annotation(getMapping)")
  public Object runWithLog(final ProceedingJoinPoint pjp, GetMapping getMapping) throws Throwable {
    return innerRunWithLog(pjp, getMapping);
  }

  // PostMapping 切点配置
  @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping) && !@annotation(com.dunshan.common.log.Unlog)")
  public void postMappingPointcut() {
  }

  @Around("postMappingPointcut() && @annotation(postMapping)")
  public Object runWithLog(final ProceedingJoinPoint pjp, PostMapping postMapping)
      throws Throwable {
    return innerRunWithLog(pjp, postMapping);
  }

  // PatchMapping 切点配置
  @Pointcut("@annotation(org.springframework.web.bind.annotation.PatchMapping) && !@annotation(com.dunshan.common.log.Unlog)")
  public void patchMappingPointcut() {
  }

  @Around("patchMappingPointcut() && @annotation(patchMapping)")
  public Object runWithLog(final ProceedingJoinPoint pjp, PatchMapping patchMapping)
      throws Throwable {
    return innerRunWithLog(pjp, patchMapping);
  }

  // PutMapping 切点配置
  @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping) && !@annotation(com.dunshan.common.log.Unlog)")
  public void putMappingPointcut() {
  }

  @Around("putMappingPointcut() && @annotation(putMapping)")
  public Object runWithLog(final ProceedingJoinPoint pjp, PutMapping putMapping) throws Throwable {
    return innerRunWithLog(pjp, putMapping);
  }

  // DeleteMapping 切点配置
  @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping) && !@annotation(com.dunshan.common.log.Unlog)")
  public void deleteMappingPointcut() {
  }

  @Around("deleteMappingPointcut() && @annotation(deleteMapping)")
  public Object runWithLog(final ProceedingJoinPoint pjp, DeleteMapping deleteMapping)
      throws Throwable {
    return innerRunWithLog(pjp, deleteMapping);
  }

  private String getUrl(Object obj) {
    String[] values = (String[]) ReflectionUtils
        .invokeMethod(ReflectionUtils.findMethod(obj.getClass(), "value"),
            obj);
    String[] paths = (String[]) ReflectionUtils
        .invokeMethod(ReflectionUtils.findMethod(obj.getClass(), "path"),
            obj);
    return values.length > 0 ? values[0] : (paths.length > 0 ? paths[0] : "Unknown URL");
  }

  @Override
  protected String preCustomMessage(ProceedingJoinPoint pjp, Object annotation,
      ExecutionStatus status) {
    return String.format(URL_LOG_FORMAT, getUrl(annotation));
  }

  @Override
  protected String customMessage(ProceedingJoinPoint pjp, Object annotation,
      ExecutionStatus status) {
    return status.isSuccessful() ? "请求成功。" : ("请求失败。" + status.getMessage());
  }

  @Override
  protected boolean needParams(ProceedingJoinPoint pjp, Object annotation, ExecutionStatus status) {
    return true;
  }

  @Override
  protected boolean needResult(ProceedingJoinPoint pjp, Object annotation, ExecutionStatus status) {
    Object result = status.getResult();
    return Objects.nonNull(result) && result.toString().length() < RESULT_MAX_LENTH;
  }

}
