package com.ityueqiangu.core.enums;

/**
 * @author BestClever
 * @title: SysFlagStatusEnum
 * @projectName roomManage
 * @description: TODO
 * @date 2020-09-14 09:52
 */
public enum SysFlagStatusEnum {
    ZERO("0","零"),
    ONE("1","一")
    ;

    private String key;
    private String value;

    private SysFlagStatusEnum(String key,String value){
        this.key=key;
        this.value=value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
