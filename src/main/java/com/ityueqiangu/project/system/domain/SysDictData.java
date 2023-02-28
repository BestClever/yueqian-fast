package com.ityueqiangu.project.system.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

import lombok.Data;
import com.ityueqiangu.core.web.domain.BaseEntity;

/**
 * 字典值表
 * @author FlowerStone
 * @title: SysDictData
 * @description:
 * @date 2022-03-22 10:10:43
 */
@Data
public class SysDictData extends BaseEntity{
	/** 主键 */
	private Integer id;
	/** 字典类型 */
	private String dictType;
	/** 字典标签 */
	private String dictLabel;
	/** 字典值 */
	private String dictValue;
	/** 字典编码 */
	private String dictCode;
	/** 回显样式 */
	private String listClass;
	/** 排序 */
	private Integer dictSort;
	/** 是否默认 */
	private String isDefault;
	/** 是否启用 */
	private String isEnable;
}