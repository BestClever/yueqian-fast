package com.ityueqiangu.system.domain;

import com.ityueqiangu.core.web.domain.BaseEntity;
import lombok.Data;
/**
 * 用户表
 */

/**
 * @author Clever、xia
 * @title: SysUser
 * @description:
 * @date 2021-04-03 19:52:56
 */
@Data
public class SysUser extends BaseEntity {
	/** 主键 */
	private Integer id;
	/** 登录名称 */
	private String loginName;
	/** 用户名称 */
	private String userName;
	/** 密码 */
	private String password;
	/** 性别 */
	private String sex;
	/** 邮箱 */
	private String email;
	/** 用户类型 */
	private String userType;
	/** 学院id */
	private Integer collegeId;
	/** 学院名称 */
	private String collegeName;
	/** 班级id */
	private Integer classeId;
	/** 班级名称 */
	private String classeName;
}