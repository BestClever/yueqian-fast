package com.ityueqiangu.system.enums;

import com.ityueqiangu.core.util.StringUtils;

/**
 * @author Clever、xia
 * @title: SystemEnums
 * @projectName teacher-evaluate-system
 * @description:
 * @date 2021-03-31 17:48
 */
public enum UserTypeEnums {
    USER_TYPE_ADMIN("1","管理员"),
    USER_TYPE_TEACHER("2","老师"),
    USER_TYPE_ASSISTANT("3","辅导员"),
    USER_TYPE_STUDENT("4","学生"),
    ;

    private String key;
    private String value;

    private UserTypeEnums(String key, String value){
        this.key=key;
        this.value=value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    /**

     * 通过value取枚举

     * @param key

     * @return

     */

    public static UserTypeEnums matchKey(String key){

        if (StringUtils.isNull(key)){
            return null;
        }
        for (UserTypeEnums enums : UserTypeEnums.values()) {

            if (StringUtils.equals(enums.getKey(),key)) {

                return enums;

            }
        }
        return null;
    }
}
