package com.dunshan.biz.model;

/**
 * @author xuxinwei
 * @create 2019-11-07
 */
public class UserMqMessage {

  private String operation;

  private User data;

  public String getOperation() {
    return operation;
  }

  public void setOperation(String operation) {
    this.operation = operation;
  }

  public User getData() {
    return data;
  }

  public void setData(User data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "UserMqMessage{" +
        "operation='" + operation + '\'' +
        ", data=" + data +
        '}';
  }
}
