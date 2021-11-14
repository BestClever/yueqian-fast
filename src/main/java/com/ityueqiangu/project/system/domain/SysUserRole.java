package com.ityueqiangu.project.system.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

import lombok.Data;
import com.ityueqiangu.core.web.domain.BaseEntity;
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