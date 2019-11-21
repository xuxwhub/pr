package com.dunshan.common.vo;


import com.dunshan.common.ErpConstants.ErrorEnum;
import com.dunshan.common.exception.BusinessErrorException;
import com.dunshan.common.exception.BusinessInfoException;
import java.io.Serializable;

public class ResultVO<T> implements Serializable {

  private static final long serialVersionUID = 3765720967319047788L;
  private final T data;
  private final int code;
  private final String msg;

  private ResultVO(int code, String msg, T data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }

  public T getData() {
    return data;
  }

  public int getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }

  public static <T> Builder<T> builder() {
    return new Builder<>();
  }

  public static <T> ResultVO<T> success(T data) {
    return ResultVO.<T>builder().success(data).build();
  }

  public static <T> ResultVO<T> success() {
    return ResultVO.<T>builder().success(null).build();
  }

  public static <T> ResultVO<T> error(String msg) {
    return ResultVO.<T>builder().businessError(new BusinessErrorException(msg)).build();
  }

  public static <T> ResultVO<T> paramError() {
    return ResultVO.<T>builder().paramError().build();
  }

  public static class Builder<T> {

    private T data;
    private int code;
    private String msg;

    private Builder() {
    }

    public Builder<T> code(int code) {
      this.code = code;
      return this;
    }

    public Builder<T> msg(String msg) {
      this.msg = msg;
      return this;
    }

    public Builder<T> data(T data) {
      this.data = data;
      return this;
    }

    public Builder<T> businessError(BusinessErrorException e) {
      this.code = e.getCode();
      this.msg = e.getMsg();
      return this;
    }

    public Builder<T> businessInfo(BusinessInfoException e) {
      this.code = e.getCode();
      this.msg = e.getMsg();
      return this;
    }

    public Builder<T> unknownError() {
      this.code = ErrorEnum.ERR_500.getIndex();
      this.msg = ErrorEnum.ERR_500.getTitle();
      return this;
    }

    public Builder<T> paramError() {
      this.code = ErrorEnum.ERR_1001.getIndex();
      this.msg = ErrorEnum.ERR_1001.getTitle();
      return this;
    }

    public Builder<T> dataRepeat() {
      this.code = ErrorEnum.ERR_1002.getIndex();
      this.msg = ErrorEnum.ERR_1002.getTitle();
      return this;
    }

    public Builder<T> success(T data) {
      this.code = ErrorEnum.SUCCESS_200.getIndex();
      this.msg = ErrorEnum.SUCCESS_200.getTitle();
      this.data = data;
      return this;
    }

    public Builder<T> noContent() {
      this.code = ErrorEnum.SUCCESS_200.getIndex();
      this.msg = ErrorEnum.SUCCESS_200.getTitle();
      return this;
    }

    public ResultVO<T> build() {
      return new ResultVO<>(this.code, this.msg, this.data);
    }
  }

  @Override
  public String toString() {
    return "[data=" + this.data + ", message=" + this.msg + ", code=" + this.code + "]";
  }
}