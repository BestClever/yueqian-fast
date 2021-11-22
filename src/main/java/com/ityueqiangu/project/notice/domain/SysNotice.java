package com.ityueqiangu.project.notice.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

import lombok.Data;
import com.ityueqiangu.core.web.domain.BaseEntity;
/**
 * 新闻公告信息表
 */

/**
 * @author FlowerStone
 * @title: SysNotice
 * @description:
 * @date 2021-11-22 15:27:11
 */
@Data
public class SysNotice extends BaseEntity{
	/** 主键 */
	private Integer id;
	/** 名称 */
	private String name;
	/** 类型 */
	private String type;
	/** 内容 */
	private String content;
	/** 是否发布 */
	private String isPublish;
	/** 发布时间 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishTime;
	/** 发布人 */
	private Integer publishBy;
	/** 是否启用 */
	private String isEnable;
}