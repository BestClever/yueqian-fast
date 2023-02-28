package com.ityueqiangu.project.userinfo.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ityueqiangu.core.web.domain.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 用户信息
 * @author FlowerStone
 * @title: User
 * @description:
 * @date 2022-04-08 22:08:42
 */
@Data
public class User extends BaseEntity {
	/** 主键 */
	private Integer id;
	/** 登录名称 */
	private String loginName;
	/** 密码 */
	private String password;
	/** 真实姓名 */
	private String userName;
	/** 身份号码 */
	private String idcardNo;
	/** 邮箱 */
	private String email;
	/** 头像 */
	private String avatar;
	/** 性别 */
	private String sex;
	/** 手机号码 */
	private String phone;
	/** 是否激活 */
	private String isActive;
	/** 最后登录时间 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastLoginTime;
	/** 注册方式 */
	private String registerType;
	/** 是否启用 */
	private String isEnable;
}