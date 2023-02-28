package com.ityueqiangu.project.system.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

import lombok.Data;
import com.ityueqiangu.core.web.domain.BaseEntity;

/**
 * 系统通知表
 * @author FlowerStone
 * @title: SysNotice
 * @description:
 * @date 2022-03-18 22:09:31
 */
@Data
public class SysNotice extends BaseEntity{
	/** 主键 */
	private Integer id;
	/** 任务id */
	private String taskId;
	/** 业务名称 */
	private String taskName;
	/** 业务id */
	private String businessId;
	/** 通知类型 */
	private String noticeType;
	/** 是否通知所有人 */
	private String isNoticeAll;
	/** 是否查阅 */
	private String isCheck;
	/** 完成时间 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date finishTime;
	/** 拥有者id */
	private String ownerId;
	/** 执行者id */
	private String actorUserId;
	/** 是否启用 */
	private String status;
}