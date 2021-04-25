package com.ityueqiangu.core.web.vo;

import lombok.Data;

/**
 * @author BestClever
 * @title: LoginUserVO
 * @projectName book-manage
 * @description: TODO
 * @date 2020-10-31 12:53
 */
@Data
public class ActiverUser {

    private Integer id;

    private String userName;

    private String loginName;

    private String password;

    private String isDeleted;

    private Integer roleId;

    private String roleName;

    private String userType;

    private String verifyCode;

}
