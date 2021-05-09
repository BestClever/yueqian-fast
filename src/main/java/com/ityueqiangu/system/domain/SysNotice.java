package com.ityueqiangu.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import lombok.Data;
import com.ityueqiangu.core.web.domain.BaseEntity;
/**
 * 消息通知表
 */

/**
 * @author Clever、xia
 * @title: SysNotice
 * @description:
 * @date 2021-05-08 16:38:48
 */
@Data
public class SysNotice extends BaseEntity{
	/** 主键 */
	private Integer id;
	/** 公告标题 */
	private String noticeTitle;
	/** 公告类型（1通知 2公告） */
	private String noticeType;
	/** 公告内容 */
	private String noticeContent;
	/** 公告状态（0正常 1关闭） */
	private String isNormal;
}