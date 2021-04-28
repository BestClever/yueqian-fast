package com.ityueqiangu.system.domain;

import lombok.Data;
import com.ityueqiangu.core.web.domain.BaseEntity;
/**
 * 角色权限表
 */

/**
 * @author Clever、xia
 * @title: SysRolePermission
 * @description:
 * @date 2021-04-27 16:58:27
 */
@Data
public class SysRolePermission extends BaseEntity{
	/** id */
	private Integer id;
	/** 角色id */
	private Integer roleId;
	/** 资源id */
	private Integer permissionId;
}