package com.ityueqiangu.project.system.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

import lombok.Data;
import com.ityueqiangu.core.web.domain.BaseEntity;

/**
 * 字典类型表
 * @author FlowerStone
 * @title: SysDictType
 * @description:
 * @date 2022-03-21 18:16:12
 */
@Data
public class SysDictType extends BaseEntity{
	/** 主键 */
	private Integer id;
	/** 名称 */
	private String dictName;
	/** 字典类型 */
	private String dictType;
	/** 字典编码 */
	private String dictCode;
	/** 是否启用 */
	private String isEnable;
}