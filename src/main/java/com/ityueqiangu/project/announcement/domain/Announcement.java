package com.ityueqiangu.project.announcement.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ityueqiangu.core.web.domain.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 新闻公告信息表
 * @author FlowerStone
 * @title: Announcement
 * @description:
 * @date 2022-04-04 22:32:40
 */
@Data
public class Announcement extends BaseEntity {
	/** 主键 */
	private Integer id;
	/** 名称 */
	private String name;
	/** 咨询编码 */
	private String infoCode;
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
	private String publishBy;
	/** 是否启用 */
	private String isEnable;

	private String publishByName;
}