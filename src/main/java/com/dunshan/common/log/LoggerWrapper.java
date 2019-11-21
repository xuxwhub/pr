package com.dunshan.common.log;

import com.dunshan.biz.util.TestFlagHolder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.Marker;

/**
 * slf4j Logger的包装类，用于封装日志的固定格式。
 *
 * @author quinn
 */
public class LoggerWrapper implements Logger {

  private final Logger logger;

  private static final String FORMAT = "^|-^|-^|-^|-^|";

  private static final String FORMAT1 = "^|-^|";

  private static final String FORMAT2 = "^|-^|-^|-^|";


  private static final Pattern patternMobile = Pattern.compile("1[3|4|5|7|8|9]{1}[0-9]{9}");

  public LoggerWrapper(Logger logger) {
    this.logger = logger;
  }

  @Override
  public String getName() {
    return this.logger.getName();
  }

  @Override
  public boolean isTraceEnabled() {
    return this.logger.isTraceEnabled();
  }

  @Override
  public void trace(String msg) {
    this.logger.trace(formatMessage(msg));
  }

  @Override
  public void trace(String format, Object arg) {
    Object[] args = desensitizationArgs(arg);
    this.logger.trace(formatMessage(format), args[0]);
  }

  @Override
  public void trace(String format, Object arg1, Object arg2) {
    Object[] args = desensitizationArgs(arg1, arg2);
    this.logger.trace(formatMessage(format), args[0], args[1]);
  }

  @Override
  public void trace(String format, Object... argArray) {
    this.logger.trace(formatMessage(format), desensitizationArgs(argArray));
  }

  @Override
  public void trace(String msg, Throwable t) {
    this.logger.trace(formatMessage(msg), t);
  }

  @Override
  public boolean isTraceEnabled(Marker marker) {
    return this.logger.isTraceEnabled(marker);
  }

  @Override
  public void trace(Marker marker, String msg) {
    this.logger.trace(marker, formatMessage(msg));
  }

  @Override
  public void trace(Marker marker, String format, Object arg) {
    Object[] args = desensitizationArgs(arg);
    this.logger.trace(marker, formatMessage(format), args[0]);
  }

  @Override
  public void trace(Marker marker, String format, Object arg1, Object arg2) {
    Object[] args = desensitizationArgs(arg1, arg2);
    this.logger.trace(marker, formatMessage(format), args[0], args[1]);
  }

  @Override
  public void trace(Marker marker, String format, Object... argArray) {
    this.logger.trace(marker, formatMessage(format), desensitizationArgs(argArray));
  }

  @Override
  public void trace(Marker marker, String msg, Throwable t) {
    this.logger.trace(marker, formatMessage(msg), t);
  }

  @Override
  public boolean isDebugEnabled() {
    return this.logger.isDebugEnabled();
  }

  @Override
  public void debug(String msg) {
    this.logger.debug(formatMessage(msg));
  }

  @Override
  public void debug(String format, Object arg) {
    Object[] args = desensitizationArgs(arg);
    this.logger.debug(formatMessage(format), args[0]);
  }

  @Override
  public void debug(String format, Object arg1, Object arg2) {
    Object[] args = desensitizationArgs(arg1, arg2);
    this.logger.debug(formatMessage(format), args[0], args[1]);
  }

  @Override
  public void debug(String format, Object... argArray) {
    this.logger.debug(formatMessage(format), desensitizationArgs(argArray));
  }

  @Override
  public void debug(String msg, Throwable t) {
    this.logger.debug(formatMessage(msg), t);
  }

  @Override
  public boolean isDebugEnabled(Marker marker) {
    return this.logger.isDebugEnabled(marker);
  }

  @Override
  public void debug(Marker marker, String msg) {
    this.logger.debug(marker, formatMessage(msg));
  }

  @Override
  public void debug(Marker marker, String format, Object arg) {
    Object[] args = desensitizationArgs(arg);
    this.logger.debug(marker, formatMessage(format), args[0]);
  }

  @Override
  public void debug(Marker marker, String format, Object arg1, Object arg2) {
    Object[] args = desensitizationArgs(arg1, arg2);
    this.logger.debug(marker, formatMessage(format), args[0], args[1]);
  }

  @Override
  public void debug(Marker marker, String format, Object... argArray) {
    this.logger.debug(marker, formatMessage(format), desensitizationArgs(argArray));
  }

  @Override
  public void debug(Marker marker, String msg, Throwable t) {
    this.logger.debug(marker, formatMessage(msg), t);
  }

  @Override
  public boolean isInfoEnabled() {
    return this.logger.isInfoEnabled();
  }

  @Override
  public void info(String msg) {
    this.logger.info(formatMessage(msg));
  }

  @Override
  public void info(String format, Object arg) {
    Object[] args = desensitizationArgs(arg);
    this.logger.info(formatMessage(format), args[0]);
  }

  @Override
  public void info(String format, Object arg1, Object arg2) {
    Object[] args = desensitizationArgs(arg1, arg2);
    this.logger.info(formatMessage(format), args[0], args[1]);
  }

  @Override
  public void info(String format, Object... argArray) {
    this.logger.info(formatMessage(format), desensitizationArgs(argArray));
  }

  @Override
  public void info(String msg, Throwable t) {
    this.logger.info(formatMessage(msg), t);
  }

  @Override
  public boolean isInfoEnabled(Marker marker) {
    return this.logger.isInfoEnabled(marker);
  }

  @Override
  public void info(Marker marker, String msg) {
    this.logger.info(marker, formatMessage(msg));
  }

  @Override
  public void info(Marker marker, String format, Object arg) {
    Object[] args = desensitizationArgs(arg);
    this.logger.info(marker, formatMessage(format), args[0]);
  }

  @Override
  public void info(Marker marker, String format, Object arg1, Object arg2) {
    Object[] args = desensitizationArgs(arg1, arg2);
    this.logger.info(marker, formatMessage(format), args[0], args[1]);
  }

  @Override
  public void info(Marker marker, String format, Object... argArray) {
    this.logger.info(marker, formatMessage(format), desensitizationArgs(argArray));
  }

  @Override
  public void info(Marker marker, String msg, Throwable t) {
    this.logger.info(marker, formatMessage(msg), t);
  }

  @Override
  public boolean isWarnEnabled() {
    return this.logger.isWarnEnabled();
  }

  @Override
  public void warn(String msg) {
    this.logger.warn(formatMessage(msg));
  }

  @Override
  public void warn(String format, Object arg) {
    Object[] args = desensitizationArgs(arg);
    this.logger.warn(formatMessage(format), args[0]);
  }

  @Override
  public void warn(String format, Object... argArray) {
    this.logger.warn(formatMessage(format), desensitizationArgs(argArray));
  }

  @Override
  public void warn(String format, Object arg1, Object arg2) {
    Object[] args = desensitizationArgs(arg1, arg2);
    this.logger.warn(formatMessage(format), args[0], args[1]);
  }

  @Override
  public void warn(String msg, Throwable t) {
    this.logger.warn(formatMessage(msg), t);
  }

  @Override
  public boolean isWarnEnabled(Marker marker) {
    return this.logger.isWarnEnabled(marker);
  }

  @Override
  public void warn(Marker marker, String msg) {
    this.logger.warn(marker, formatMessage(msg));
  }

  @Override
  public void warn(Marker marker, String format, Object arg) {
    Object[] args = desensitizationArgs(arg);
    this.logger.warn(marker, formatMessage(format), args[0]);
  }

  @Override
  public void warn(Marker marker, String format, Object arg1, Object arg2) {
    Object[] args = desensitizationArgs(arg1, arg2);
    this.logger.warn(marker, formatMessage(format), args[0], args[1]);
  }

  @Override
  public void warn(Marker marker, String format, Object... argArray) {
    this.logger.warn(marker, formatMessage(format), desensitizationArgs(argArray));
  }

  @Override
  public void warn(Marker marker, String msg, Throwable t) {
    this.logger.warn(marker, formatMessage(msg), t);
  }

  @Override
  public boolean isErrorEnabled() {
    return this.logger.isErrorEnabled();
  }

  @Override
  public void error(String msg) {
    this.logger.error(formatMessage(msg));
  }

  @Override
  public void error(String format, Object arg) {
    Object[] args = desensitizationArgs(arg);
    this.logger.error(formatMessage(format), args[0]);
  }

  @Override
  public void error(String format, Object arg1, Object arg2) {
    Object[] args = desensitizationArgs(arg1, arg2);
    this.logger.error(formatMessage(format), args[0], args[1]);
  }

  @Override
  public void error(String format, Object... argArray) {
    this.logger.error(formatMessage(format), desensitizationArgs(argArray));
  }

  @Override
  public void error(String msg, Throwable t) {
    this.logger.error(formatMessage(msg), t);
  }

  @Override
  public boolean isErrorEnabled(Marker marker) {
    return this.logger.isErrorEnabled(marker);
  }

  @Override
  public void error(Marker marker, String msg) {
    this.logger.error(marker, formatMessage(msg));
  }

  @Override
  public void error(Marker marker, String format, Object arg) {
    Object[] args = desensitizationArgs(arg);
    this.logger.error(marker, formatMessage(format), args[0]);
  }

  @Override
  public void error(Marker marker, String format, Object arg1, Object arg2) {
    Object[] args = desensitizationArgs(arg1, arg2);
    this.logger.error(marker, formatMessage(format), args[0], args[1]);
  }

  @Override
  public void error(Marker marker, String format, Object... argArray) {
    this.logger.error(marker, formatMessage(format), desensitizationArgs(argArray));
  }

  @Override
  public void error(Marker marker, String msg, Throwable t) {
    this.logger.error(marker, formatMessage(msg), t);
  }

  private static String formatMessage(String msg) {
    try {
      StackTraceElement[] temp = Thread.currentThread().getStackTrace();
      StackTraceElement element = temp[3];
      return desensitization(
          new StringBuilder(element.getClassName()).append(".").append(element.getMethodName())
              .append(FORMAT1).append(TestFlagHolder.get4log()).append(FORMAT2)
              .append(msg).toString());
    } catch (NullPointerException e) {
      return new StringBuilder("-").append(FORMAT1).append(TestFlagHolder.get4log()).append(FORMAT2).append(msg).toString();
    }
  }

  private static final class LogArgProxy {

    private Object obj;

    public LogArgProxy(Object obj) {
      this.obj = obj;
    }

    @Override
    public String toString() {
      return desensitization(this.obj.toString());
    }
  }

  private static Object[] desensitizationArgs(Object... args) {
    if (args == null || args.length == 0) {
      return new Object[] {args};
    }
    int length = args.length;
    List<Object> argList = new ArrayList<>(length);
    for (int i = 0; i < length; i++) {
      Object arg = args[i];
      Object proxy = "";
      if (arg != null) {
        proxy = new LogArgProxy(arg);
        if (i == length - 1 && arg instanceof Throwable) { // 如果是最后一个参数 判断是否异常,如果异常直接 返回
          proxy = arg;
        }
      }
      argList.add(proxy);
    }
    return argList.toArray(new Object[args.length]);
  }

  //手机号脱敏，隐藏中间4位信息。
  private static String desensitization(String str) {
//    Matcher matcher = patternMobile.matcher(str);
//    StringBuffer buffer = new StringBuffer();
//    while (matcher.find()) {
//      String group = matcher.group();
//      matcher.appendReplacement(buffer,
//          new StringBuilder(group.substring(0, 3)).append("****").append(group.substring(7))
//              .toString());
//    }
//    matcher.appendTail(buffer);
    return str.toString();
  }
}
