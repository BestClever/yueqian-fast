package com.ityueqiangu.common.enums;

/**
 * @author Clever、xia
 * @title: DeptEnum
 * @projectName one_cartoon
 * @description:
 * @date 2020-12-22 14:39
 */
public enum DeptEnum {
    NOT_CERTIFIY_DEPT("203","未认证的部门")
    ;

    private String code;

    private String value;

    DeptEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }}
