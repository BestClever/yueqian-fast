package com.ityueqiangu.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ityueqiangu.core.web.domain.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
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
	/** 主键id */
	private Integer id;
	/** 用户名 */
	private String userName;
	/** 登录名 */
	private String loginName;
	/** 密码 */
	private String password;
	/** 联系方式 */
	private String contactInformation;
	/** 地址 */
	private String address;
	/** 性别 */
	private String sex;
	/** 部门id */
	private Integer deptId;
	/** 排序 */
	private String sortNum;
	/** 头像 */
	private String avatar;
	/** 上级领导id */
	private Integer upperLeaderId;
	/** 上级领导名称 */
	private String upperLeaderName;
	/** 受雇日期 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date hireDate;
	/** 盐值 */
	private String salt;
	/** 是否有效 */
	private String isAvailable;

	private String roleIds;
}