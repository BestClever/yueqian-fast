package com.ityueqiangu.common.enums;

/**
 * @author Clever、xia
 * @title: CardInfoEnum
 * @projectName one_cartoon
 * @description:
 * @date 2020-12-23 17:45
 */
public enum  CardInfoEnum {

    CARD_LOSS_YES("1","丢失"),
    CARD_LOSS_NO("0","未丢失"),
    CARD_UNLOCK_YES("0","未解锁"),
    CARD_UNLOCK_NO("1","已锁"),
    CARD_AUTHENTICATE_NO("0","未认证"),
    CARD_AUTHENTICATE_YES("1","已认证"),
    DEAL_REPORT_YES("1","已处理"),
    DEAL_REPORT_NO("0","未处理")

    ;

    private String code;

    private String value;

    CardInfoEnum(String code, String value) {
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
