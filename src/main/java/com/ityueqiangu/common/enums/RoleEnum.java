package com.ityueqiangu.common.enums;

/**
 * @author FlowerStone
 * @title: RoleEnum
 * @projectName
 * @description:
 * @date 2020-12-21 19:27
 */
public enum RoleEnum {
    NOT_CERTIFIY_USER("101", "未认证用户"),
    COMMENT_USER("100", "普通用户"),
    BUS_COMPANY_USER("2", "公交公司管理员"),
    ;

    private String code;

    private String value;

    RoleEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
