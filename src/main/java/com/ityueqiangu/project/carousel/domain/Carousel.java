package com.ityueqiangu.project.carousel.domain;


import com.ityueqiangu.core.web.domain.BaseEntity;
import lombok.Data;
/**
 * 轮播图
 */

/**
 * @author FlowerStone
 * @title: Carousel
 * @description:
 * @date 2021-11-19 11:51:44
 */
@Data
public class Carousel extends BaseEntity {
	/** 主键 */
	private Integer id;
	/** 图片名称 */
	private String name;
	/** 跳转路径 */
	private String url;
	/** 图片地址 */
	private String path;
	/** 是否展示 */
	private String isDisplay;
	/** 类型 0.管理系统 1.门户 */
	private String type;
}