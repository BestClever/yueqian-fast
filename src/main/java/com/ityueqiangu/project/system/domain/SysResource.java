package com.ityueqiangu.project.system.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

import lombok.Data;
import com.ityueqiangu.core.web.domain.BaseEntity;
/**
 * 系统资源
 */

/**
 * @author FlowerStone
 * @title: SysResource
 * @description:
 * @date 2021-11-14 10:38:35
 */
@Data
public class SysResource extends BaseEntity{
	/** 主键 */
	private Integer id;
	/** 资源名称 */
	private String resourceName;
	/** 资源类型 */
	private String resourceType;
	/** 父id */
	private Integer parentId;
	/** 请求路径 */
	private String requestUrl;
	/** 打开方式 */
	private String openType;
	/** 图标 */
	private String icon;
	/** 排序 */
	private Integer sort;
	/** 是否启用 */
	private String isEnable;
}