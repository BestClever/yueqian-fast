package com.ityueqiangu.core.web.exception;

import com.ityueqiangu.common.exception.BusinessException;
import com.ityueqiangu.common.exception.DemoModeException;
import com.ityueqiangu.common.utils.ServletUtils;
import com.ityueqiangu.core.web.domain.ResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 *
 * @author FlowerStone
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 权限校验失败 如果请求为ajax返回json，普通请求跳转页面

     @ExceptionHandler(AuthorizationException.class) public Object handleAuthorizationException(HttpServletRequest request, AuthorizationException e)
     {
     log.error(e.getMessage(), e);
     if (ServletUtils.isAjaxRequest(request))
     {
     return ResponseInfo.error(PermissionUtils.getMsg(e.getMessage()));
     }
     else
     {
     ModelAndView modelAndView = new ModelAndView();
     modelAndView.setViewName("error/unauth");
     return modelAndView;
     }
     }*/

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseInfo handleException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
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
        log.error(e.getMessage(), e);
        return ResponseInfo.error("服务器错误，请联系管理员");
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public Object businessException(HttpServletRequest request, BusinessException e) {
        log.error(e.getMessage(), e);
        if (ServletUtils.isAjaxRequest(request)) {
            return ResponseInfo.error(e.getMessage());
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("errorMessage", e.getMessage());
            modelAndView.setViewName("error/business");
            return modelAndView;
        }
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public ResponseInfo validatedBindException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return ResponseInfo.error(message);
    }

    /**
     * 演示模式异常
     */
    @ExceptionHandler(DemoModeException.class)
    public ResponseInfo demoModeException(DemoModeException e) {
        return ResponseInfo.error("演示模式，不允许操作");
    }
}
