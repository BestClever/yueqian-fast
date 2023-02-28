package com.ityueqiangu.core.exception;

/**
 * 业务异常
 *
 * @author FlowerStone
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private Integer code;

    protected String message;

    public BusinessException() {
    }

    public BusinessException(String message) {
        this.message = message;
    }

    public BusinessException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
