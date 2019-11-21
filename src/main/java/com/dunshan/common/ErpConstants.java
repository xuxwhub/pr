package com.dunshan.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量类
 * <P>File name : PopCommonConstants.java </P>
 * <P>Author : michael </P>
 * <P>Date : 2015年7月8日 </P>
 */
public final class ErpConstants {

  /**
   * 错误常量
   * <P>File name : PopCommonConstants.java </P>
   * <P>Author : michael </P>
   * <P>Date : 2015年7月11日 </P>
   */
  public enum ErrorEnum {
    SUCCESS_200("成功", 200),
    SUCCESS_201("部分成功", 201),
    SUCCESS_202("账号同时在其它地方登录", 202),
    ERR_302("会话已过期，请重新登录。", 302),
    ERR_409("操作失败，数据不存在或版本有变", 409),

    ERR_404("请求方法不存在", 404),
    ERR_405("请求未授权", 405),
    ERR_500("服务器开小差。", 500),
    ERR_700("用户名密码错误。", 700),
    ERR_701("验证码错误。", 701),
    ERR_702("用户已被锁定。", 702),
    ERR_900("修改密码失败。", 900),
    // 默认业务异常
    ERR_1000("业务失败", 1000),
    ERR_1001("缺少参数或参数值错误", 1001),
    ERR_1002("数据重复", 1002),
    ERR_1003("不能进行此业务", 1003);

    private String title;
    private int index;

    private ErrorEnum(String title, int index) {
      this.title = title;
      this.index = index;
    }

    public int getIndex() {
      return this.index;
    }

    public String getTitle() {
      return this.title;
    }

    public static String getTitle(int index) {
      String title = "";
      for (ErrorEnum errorEnum : ErrorEnum.values()) {
        if (index == errorEnum.index) {
          title = errorEnum.title;
        }
      }
      return title;
    }

    public static ErrorEnum getErrorEnum(int index) {
      ErrorEnum returnEnum = null;
      for (ErrorEnum errorEnum : ErrorEnum.values()) {
        if (index == errorEnum.index) {
          returnEnum = errorEnum;
        }
      }
      return returnEnum;
    }

    public static Map<String, String> getErrorEnumMap() {
      Map<String, String> map = new HashMap<String, String>();
      for (ErrorEnum errorEnum : ErrorEnum.values()) {
        map.put(String.valueOf(errorEnum.index), errorEnum.title);
      }
      return map;
    }
  }

}
