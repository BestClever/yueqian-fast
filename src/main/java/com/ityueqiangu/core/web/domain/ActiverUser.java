package com.ityueqiangu.core.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author FlowerStone
 * @date 2021-11-10 21:32:22
 */
@Data
public class ActiverUser {

    private Integer id;

    /*用户id*/
    private Integer userId;

    private String userName;

    private String email;

    @NotBlank(message = "登录名称不能为空")
    private String loginName;

    @JsonIgnore
    @NotBlank(message = "密码不能为空")
    private String password;

    /** 头像 */
    private String avatar;

    private String isDeleted;

    private Integer roleId;

    private Integer deptId;

    private String roleName;

    private String captcha;
}
