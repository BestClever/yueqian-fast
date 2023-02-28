package com.ityueqiangu.core.web.exception;


import cn.hutool.core.util.ObjectUtil;
import com.ityueqiangu.core.exception.BusinessException;
import com.ityueqiangu.core.exception.DemoModeException;
import com.ityueqiangu.core.utils.ServletUtils;
import com.ityueqiangu.core.web.domain.ResponseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

/**
 * 全局异常处理器
 *
 * @author FlowerStone
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseInfo handleException(HttpRequestMethodNotSupportedException e) {
        log.error("HttpRequestMethodNotSupportedException====>" + e.getMessage(), e);
        return ResponseInfo.error("不支持' " + e.getMethod() + "'请求");
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseInfo notFount(RuntimeException e) {
        log.error("运行时异常:", e);
        return ResponseInfo.error("运行时异常:" + e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseInfo handleException(Exception e) {
        log.error("Exception====>" + e.getMessage(), e);
        return ResponseInfo.error("服务器错误，请联系管理员");
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public Object businessException(Object handler, BusinessException e) {
        log.error("BusinessException====>" + e.getMessage(), e);
        Integer code = e.getCode();
        return ObjectUtil.isNotEmpty(code) ? ResponseInfo.error(code, e.getMessage()) : ResponseInfo.error(e.getMessage());
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public ResponseInfo validatedBindException(BindException e) {
        log.error("BindException====>" + e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return ResponseInfo.error(message);
    }


    /**
     * 方法参数校验
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseInfo handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException====>" + e.getMessage(), e);
        return ResponseInfo.error(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * ValidationException
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseInfo handleValidationException(ValidationException e) {
        log.error("ValidationException====>" + e.getMessage(), e);
        return ResponseInfo.error(e.getCause().getMessage());
    }

    /**
     * ConstraintViolationException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseInfo handleConstraintViolationException(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        return ResponseInfo.error(e.getMessage());
    }

    /**
     * 演示模式异常
     */
    @ExceptionHandler(DemoModeException.class)
    public ResponseInfo demoModeException(DemoModeException e) {
        return ResponseInfo.error("演示模式，不允许操作");
    }
}
