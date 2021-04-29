package com.ityueqiangu.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import lombok.Data;
import com.ityueqiangu.core.web.domain.BaseEntity;
/**
 * 权限表
 */

/**
 * @author Clever、xia
 * @title: SysPermission
 * @description:
 * @date 2021-04-27 16:58:27
 */
@Data
public class SysPermission extends BaseEntity{
	private Integer id;
	/** 权限名称 */
	private String permissionName;
	/** 权限类型 */
	private String permissionType;
	/** 权限标识 */
	private String permissionCode;
	/** 权限url */
	private String permissionUrl;
	/** 打开方式 */
	private String openType;
	/** 父类编号 */
	private Integer parentId;
	/** 图标 */
	private String icon;
	/** 排序 */
	private String sortNum;
	/** 是否展开 */
	private String isSpread;
	/** 是否可用 */
	private String isAvailable;
	/*父节点名称*/
	private String parentName;

	/**
	 * 计算列 提供给前端组件
	 * */
	private String checkArr = "0";
}