package com.ityueqiangu.core.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ityueqiangu.project.system.domain.SysUser;
import lombok.Data;

/**
 * @author FlowerStone
 * @date 2021-11-10 21:32:22
 */
@Data
public class ActiverUser {

    private Integer id;

    private String userName;

    private String loginName;

    @JsonIgnore
    private String password;

    private String isDeleted;

    private Integer roleId;

    private Integer deptId;

    private String roleName;

    private String captcha;

    private SysUser sysUser;
}
