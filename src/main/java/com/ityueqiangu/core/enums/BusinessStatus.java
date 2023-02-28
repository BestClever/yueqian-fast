package com.ityueqiangu.core.enums;

/**
 * 操作状态
 *
 * @author FlowerStone
 */
public enum BusinessStatus {
    /**
     * 成功
     */
    SUCCESS("1"),

    /**
     * 失败
     */
    FAIL("0"),
    ;

    private String value;

    BusinessStatus(String value) {
        this.value = value;
    }

    public static void main(String[] args) {
        System.out.println(BusinessStatus.SUCCESS.value);
        for (BusinessStatus value : BusinessStatus.values()) {
            System.out.println(value.value);

        }
    }
}
