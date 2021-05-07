package com.ityueqiangu.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import lombok.Data;
import com.ityueqiangu.core.web.domain.BaseEntity;
/**
 * 角色表
 */

/**
 * @author Clever、xia
 * @title: SysRole
 * @description:
 * @date 2021-04-27 16:58:27
 */
@Data
public class SysRole extends BaseEntity{
	/** 主键 */
	private Integer id;
	/** 角色名称 */
	private String roleName;
	/** 角色编码 */
	private String roleCode;
	/*角色描述*/
	private String roleDescription;
	/** 是否可用 */
	private String isAvailable;


	private boolean checked = false;
}