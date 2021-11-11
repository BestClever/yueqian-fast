package com.ityueqiangu.project.system.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

import lombok.Data;
import com.ityueqiangu.core.web.domain.BaseEntity;
/**
 * 系统用户表

 */

/**
 * @author FlowerStone
 * @title: SysUser
 * @description:
 * @date 2021-11-10 21:46:24
 */
@Data
public class SysUser extends BaseEntity{
    /** 编号 */
    private String id;
    /** 部门编号 */
    private String deptId;
    /** 账户 */
    private String userName;
    /** 密码 */
    private String password;
    /** 姓名 */
    private String salt;
    /** 姓名 */
    private String realName;
    /** 邮箱 */
    private String email;
    /** 头像 */
    private String avatar;
    /** 性别 */
    private String sex;
    /** 电话 */
    private String phone;
    /** 是否启用 */
    private String isEnable;
    /** 是否激活 */
    private String isActive;
    /** 是否登录 */
    private String isLogin;
    /** 最后一次登录时间 */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastLoginTime;
    /** 创建人 */
    private String createBy;
    /** 修改人 */
    private String updateBy;
}