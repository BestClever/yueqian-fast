package com.ityueqiangu.core.enums;


import com.ityueqiangu.core.exception.BaseInfoInterface;

/**
 *  公用描述枚举类
 * @author halfsummer
 * @Title: CommonEnum
 * @Version:1.0.0
 * @date 2018年6月25日
 */
public enum CommonEnum implements BaseInfoInterface {
    // 数据操作错误定义
    OPERATE_SUCCESS(20000,"操作成功！"),
    OPERATE_FAILURE(20500,"操作失败！"),
    SUCCESS(200, "成功!"),
    BODY_NOT_MATCH(400, "请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH(401, "请求的数字签名不匹配!"),
    FORBIDDEN(403,"没有权限！"),
    NOT_FOUND(404, "未找到该资源!"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
    SERVER_BUSY(503, "服务器正忙，请稍后再试!"),
    ARITHMETIC_ERROR(1100, "数学运算错误!"),
    REPERTORY_FULL(1101,"文件库已满！"),
    DATA_EXIST(1200, "数据存在!"),
    DATA_NOT_EXIST(1250, "数据不存存在!"),

    /*登录*/
    LOGIN_SUCCESS(9001, "登录成功！"),
    REGISTER_SUCCESS(9002, "注册成功！"),
    LOGIN_FAILURE(9050,"登录失败！"),
    PWD_ERROR(9052,"密码错误"),
    ACCOUNT_DISABLED(9053,"账号停用,请联系管理员！"),

    /**查询**/
    SELECT_SUCCESS(2000,"查询成功！"),
    SELECT_FAILUER(2050,"查询失败！"),
    PARAM_EMPTY(2051,"参数为空!"),

    /*添加*/
    SAVE_SUCESS(9101, "新增成功！"),
    SAVE_FAILURE(9151, "新增失败！"),
    /*更新*/
    UPDATE_SUCESS(9201, "更新成功！"),
    UPDATE_FAILURE(9251, "更新失败！"),
    /*删除*/
    DELETE_SUCESS(9301, "删除成功！"),
    DELETE_FAILURE(9351, "删除失败！"),

    /*用户 */
    USER_NOT_EXIST(10001,"用户不存在！"),
    PASSWORD_ERROR(10002,"密码错误！"),
    ACCOUNT_LOCKED(10003,"账户被锁定！"),



    ;

    /**
     * 错误码
     */
    private Integer resultCode;

    /**
     * 错误描述
     */
    private String resultMsg;

    CommonEnum(Integer resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public Integer getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }

}
