package com.ityueqiangu.core.web.vo;

import com.ityueqiangu.system.domain.SysUser;
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

    private Integer deptId;

    private String roleName;

    private String verifyCode;

    private SysUser sysUser;

}
