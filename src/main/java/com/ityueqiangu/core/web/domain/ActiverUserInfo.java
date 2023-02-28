package com.ityueqiangu.core.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ityueqiangu.project.system.domain.SysUser;
import com.ityueqiangu.project.userinfo.domain.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 前端用户vo
 *
 * @author FlowerStone
 * @date 2021-11-10 21:32:22
 */
@Data
public class ActiverUserInfo {

    /*用户id*/
    private Integer userId;
    /*用户名称*/
    private String userName;
    /*邮箱地址*/
    private String email;
    /*登录名称*/
    @NotBlank(message = "登录名称不能为空")
    private String loginName;
    /*密码*/
    @JsonIgnore
    @NotBlank(message = "密码不能为空")
    private String password;
    /** 性别 */
    private String sex;
    /**
     * 头像
     */
    private String avatar;
    /** 身份号码 */
    private String idcardNo;
    /** 手机号码 */
    private String phone;
    /*是否删除*/
    private String isDeleted;
    /*验证码*/
    private String captcha;

    private User user;

}
