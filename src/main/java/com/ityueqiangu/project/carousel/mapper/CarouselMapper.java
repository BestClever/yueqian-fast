package com.ityueqiangu.project.carousel.mapper;

import com.ityueqiangu.project.carousel.domain.Carousel;

import java.util.List;

/**
 * @author FlowerStone
 * @title: CarouselMapper
 * @description:
 * @date 2021-11-19 11:51:44
 */
public interface CarouselMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<Carousel> selectCarouselList(Carousel carousel);


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	Carousel selectCarouselById(Integer id);
	
	/**
     * 新增，插入所有字段
     *
     * @param carousel 新增的记录
     * @return 返回影响行数
     */
	Integer insertCarousel(Carousel carousel);
	
	
	/**
     * 修改
     *
     * @param carousel 修改的记录
     * @return 返回影响行数
     */
	Integer updateCarousel(Carousel carousel);
	

	/**
     * 删除记录
     *
     * @param id
     * @return 返回影响行数
     */
	Integer deleteCarouselById(Integer id);
    
    /**
     * 根据条件删除记录
     *
     * @param carousel}
     * @return 返回影响行数
     */
	Integer deleteCarouselByCondition(Carousel carousel);
	
}