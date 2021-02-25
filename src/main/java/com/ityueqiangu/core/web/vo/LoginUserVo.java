package com.ityueqiangu.core.web.vo;

import lombok.Data;

/**
 * @author BestClever
 * @title: LoginUserVO
 * @projectName
 * @description: TODO
 * @date 2020-10-31 12:53
 */
@Data
public class LoginUserVo {
    /**
     * 登录名称
     */
    private String userName;

    /**
     * 头像
     */
    private String avatar;

    /** 是否有效(0.有效,1.无效) */
    private String isAvailable;

    /**
     * 角色
     */
    private String roleName;

}
