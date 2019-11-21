package com.dunshan.biz.util;

/**
 * @author xuxinwei
 * @create 2019-11-20
 */
public class TestFlagHolder {

  private final static ThreadLocal<String> TEST_FLAG = new ThreadLocal<>();

  public static void set(String value) {
    TEST_FLAG.set(value);
  }

  public static String get() {
    return TEST_FLAG.get();
  }

  public static String get4log() {
    if (TEST_FLAG.get() == null) {
      return "-";
    }
    return TEST_FLAG.get();
  }

  public static void remove() {
    TEST_FLAG.remove();
  }

}
