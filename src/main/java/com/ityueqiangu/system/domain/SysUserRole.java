package com.ityueqiangu.system.domain;

import lombok.Data;
import com.ityueqiangu.core.web.domain.BaseEntity;
/**
 * 用户角色表
 */

/**
 * @author Clever、xia
 * @title: SysUserRole
 * @description:
 * @date 2021-04-27 16:58:27
 */
@Data
public class SysUserRole extends BaseEntity{
	private Integer id;
	/** 用户id */
	private Integer userId;
	private Integer roleId;
}