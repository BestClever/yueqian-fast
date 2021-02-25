package com.ityueqiangu.core.web.result;



/**
 * ResponseData:
 *
 */

public class ResultInfo<T> {

    /**
     * 返回是否成功
     */
    private boolean success;

    /**
     * 统一返回码
     */
    public Integer code;

    /**
     * 统一错误消息
     */
    public String msg;

    /**
     * 结果对象
     */
    public T data;

    public boolean isSuccess() {
        return success;
    }

    public ResultInfo setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public ResultInfo() {
    }

    public ResultInfo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultInfo(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public ResultInfo setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResultInfo setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResultInfo setData(T data) {
        this.data = data;
        return this;
    }
}
