package com.ityueqiangu.project.system.domain;


import lombok.Data;
/**
 * 系统用户角色表
 */

/**
 * @author FlowerStone
 * @title: SysUserRole
 * @description:
 * @date 2021-11-14 07:11:02
 */
@Data
public class SysUserRole{
	/** 主键 */
	private Integer id;
	/** 用户id */
	private Integer userId;
	/** 角色id */
	private Integer roleId;
}