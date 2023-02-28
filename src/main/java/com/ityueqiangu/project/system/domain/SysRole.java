package com.ityueqiangu.project.system.domain;


import com.ityueqiangu.core.web.domain.BaseEntity;
import lombok.Data;
/**
 * 系统角色表
 */

/**
 * @author FlowerStone
 * @title: SysRole
 * @description:
 * @date 2021-11-13 14:05:24
 */
@Data
public class SysRole extends BaseEntity {
	/** 主键 */
	private Integer id;
	/** 角色名称 */
	private String roleName;
	/** 角色标识 */
	private String roleCode;
	/** 排序 */
	private Integer sort;
	/** 是否启用 */
	private String isEnable;

	/**
	 * 提供前端 显示
	 * */
	private boolean checked = false;
}