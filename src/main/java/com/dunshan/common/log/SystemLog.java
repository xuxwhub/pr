package com.dunshan.common.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>此注解用来供开发者标注打印日志，只能作用于方法，且方法所在的bean必须是被spring容器所管理的。
 * 如果某个功能方法需要记录日志，那么则需要对其添加此注解。value值为成功时记录的消息。</p>
 *
 * @author quinn
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SystemLog {

  String value() default "方法执行成功。";

  /**
   * 是否在日志中打印方法参数，需要每个参数实现toString方法。
   */
  boolean enableArgsLog() default false;

  /**
   * 是否只有在出错时才打印方法参数，作为enableArgsLog的补充， 如果为true，则只在方法抛出异常时打印参数；否则，任何时候都打印参数。
   */
  boolean argsOnlyOnError() default false;

  /**
   * 是否在日志中打印方法返回值，需要每个参数实现toString方法。
   */
  boolean enableResultLog() default true;

}