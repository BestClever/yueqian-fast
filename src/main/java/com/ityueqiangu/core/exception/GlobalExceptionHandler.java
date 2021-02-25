package com.ityueqiangu.core.exception;

import com.ityueqiangu.core.enums.CommonEnum;
import com.ityueqiangu.core.web.result.ResultDataUtil;
import com.ityueqiangu.core.web.result.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;


/**
 * 
* @Title: GlobalExceptionHandler
* @Description: 全局异常处理
如果使用@RestControllerAdvice 注解
则会将返回的数据类型转换成JSON格式
* @Version:1.0.0  
* @author halfsummer
* @date 2020年5月27日
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	/**
	 * 处理自定义的业务异常
	 * @param req
	 * @param e
	 * @return
	 */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
	public ResultInfo bizExceptionHandler(HttpServletRequest req, BizException e){
    	logger.error("发生业务异常！原因是：{}",e.getErrorMsg());
    	return ResultDataUtil.createFail(e.getErrorCode(),e.getErrorMsg());
    }

	/**
	 * 处理空指针的异常
	 * @param req
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value =NullPointerException.class)
	@ResponseBody
	public ResultInfo exceptionHandler(HttpServletRequest req, NullPointerException e){
		logger.error("发生空指针异常！原因是:",e);
		return ResultDataUtil.createFail(CommonEnum.BODY_NOT_MATCH);
	}


    /**
        * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
	@ResponseBody
	public ResultInfo exceptionHandler(HttpServletRequest req, Exception e){
    	logger.error("未知异常！原因是:",e);
       	return ResultDataUtil.createFail(CommonEnum.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ArithmeticException.class)
	@ResponseBody
	public ResultInfo arithmeticExceptionHandler(HttpServletRequest req, Exception e){
		logger.error("未知异常！原因是:",e);
		return ResultDataUtil.createFail(CommonEnum.ARITHMETIC_ERROR);
	}

}
