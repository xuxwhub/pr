package com.dunshan.common.log;

import org.slf4j.Logger;

/**
 * slf4j的自定义LoggerFactory，使用包装的LoggerWrapper。
 *
 * @author quinn
 * @see LoggerWrapper
 */
public class LoggerFactory {

  public static Logger getLogger(String name) {
    return new LoggerWrapper(org.slf4j.LoggerFactory.getLogger(name));
  }

  public static Logger getLogger(Class<?> clazz) {
    return new LoggerWrapper(org.slf4j.LoggerFactory.getLogger(clazz));
  }

}
