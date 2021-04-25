package com.ityueqiangu.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import lombok.Data;
import com.ityueqiangu.core.web.domain.BaseEntity;
/**
 * 部门表
 */

/**
 * @author Clever、xia
 * @title: SysDept
 * @description:
 * @date 2021-04-25 17:33:01
 */
@Data
public class SysDept extends BaseEntity{
	private Integer id;
	/** 部门名称 */
	private String deptName;
	/** 父类编码 */
	private Integer parentId;
	private String deptDescription;
	/** 是否展开 */
	private String isSpread;
	/** 是否有效 */
	private String isAvailable;
	/** 排序 */
	private String sortNum;
}