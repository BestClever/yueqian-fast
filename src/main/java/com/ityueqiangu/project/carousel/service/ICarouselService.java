package com.ityueqiangu.project.carousel.service;

import com.ityueqiangu.project.carousel.domain.Carousel;

import java.util.List;

/**
 * @author FlowerStone
 * @title: CarouselService
 * @description:
 * @date 2021-11-19 11:51:44
 */
public interface ICarouselService {

    /**
     * 查询所有记录
     *
     * @return list集合
     */
    List<Carousel> selectCarouselList(Carousel carousel);


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回Carousel
     */
    Carousel selectCarouselById(Integer id);
	
    /**
     * 新增
     *
     * @param carousel 新增的记录
     * @return 返回ResultInfo
     */
    Integer insertCarousel(Carousel carousel);
	
	
    /**
     * 修改，修改所有字段
     *
     * @param carousel 修改的记录
     * @return 返回ResultInfo
     */
    Integer updateCarousel(Carousel carousel);
	
    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    Integer deleteCarouselById(Integer id);
	
}