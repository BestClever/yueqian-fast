package com.ityueqiangu.project.system.domain;


import lombok.Data;
/**
 * 系统角色资源表
 */

/**
 * @author FlowerStone
 * @title: SysRoleResource
 * @description:
 * @date 2021-11-14 07:11:02
 */
@Data
public class SysRoleResource{
	/** 主键 */
	private Integer id;
	/** 角色id */
	private Integer roleId;
	/** 资源id */
	private Integer resourceId;

	private String resourceIds;
}