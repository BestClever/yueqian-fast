package com.ityueqiangu.core.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ityueqiangu.project.system.domain.SysUser;
import lombok.Data;

/**
 * @author FlowerStone
 * @date 2021-11-10 21:32:22
 */
@Data
public class ActiverUser<T> {

    private Integer id;

    /*用户id*/
    private Integer userId;

    private String userName;

    private String loginName;

    @JsonIgnore
    private String password;
    /** 头像 */
    private String avatar;

    private String isDeleted;

    private Integer roleId;

    private Integer deptId;

    private String roleName;

    private String captcha;

    private T userInfo;
}
