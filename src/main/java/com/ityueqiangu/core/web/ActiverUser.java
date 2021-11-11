package com.ityueqiangu.core.web;

import lombok.Data;

/**
 * @author xiacong
 * @date 2021-11-10 21:32:22
 */
@Data
public class ActiverUser {

    private Integer id;

    private String userName;

    private String loginName;

    private String password;

    private String isDeleted;

    private Integer roleId;

    private Integer deptId;

    private String roleName;

    private String verifyCode;

//    private SysUser sysUser;
}
