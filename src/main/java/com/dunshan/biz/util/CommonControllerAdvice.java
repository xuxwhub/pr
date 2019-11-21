package com.dunshan.biz.util;


import com.dunshan.common.ErpConstants.ErrorEnum;
import com.dunshan.common.exception.BusinessErrorException;
import com.dunshan.common.exception.BusinessInfoException;
import com.dunshan.common.vo.ResultVO;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice("com.dunshan.biz")
public class CommonControllerAdvice {

  private static final Logger logger = LoggerFactory.getLogger(CommonControllerAdvice.class);

  @ExceptionHandler(value = Exception.class)
  public @ResponseBody
  ResultVO<?> handleException(Exception exception, WebRequest request) {
    logException(exception, request);
    return ResultVO.builder().unknownError()
        .data(exception.toString() + "\n" + Arrays.asList(exception.getStackTrace()).toString())
        .build();
  }

  @ExceptionHandler(value = BusinessErrorException.class)
  public @ResponseBody
  ResultVO<?> handleBusiness(BusinessErrorException exception, WebRequest request) {
    logException(exception, request);
    return ResultVO.builder().businessError(exception).build();
  }

  @ExceptionHandler(value = BusinessInfoException.class)
  public @ResponseBody
  ResultVO<?> handleBusiness(BusinessInfoException exception, WebRequest request) {
    logger.warn("业务异常信息：【{}】{}。", exception.getCode(), exception.getMsg());
    return ResultVO.builder().businessInfo(exception).build();
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public @ResponseBody
  ResultVO<?> handleValidation(MethodArgumentNotValidException exception, WebRequest request) {
    logException(exception, request);
    return validationResult(exception.getBindingResult());
  }

  @ExceptionHandler(value = BindException.class)
  public @ResponseBody
  ResultVO<?> handleBindException(BindException exception, WebRequest request) {
    logException(exception, request);
    return validationResult(exception.getBindingResult());
  }

  private ResultVO<Object> validationResult(BindingResult bindResult) {
    return ResultVO.builder().code(ErrorEnum.ERR_1001.getIndex()).msg(bindResult.getAllErrors()
        .stream().map(CommonControllerAdvice::getMessage)
        .collect(Collectors.joining(";"))).build();
  }

  private static String getMessage(ObjectError error) {
    if (error instanceof FieldError) {
      return ((FieldError) error).getField() + error.getDefaultMessage();
    }
    return error.getDefaultMessage();
  }

  private void logException(Throwable exception, WebRequest request) {
    logger.error("接口:[" + request.toString() + "]出现异常：", exception);
  }
}