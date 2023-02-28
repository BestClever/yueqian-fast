package com.ityueqiangu.project.carousel.service.impl;

import com.ityueqiangu.core.constant.Constants;
import com.ityueqiangu.project.carousel.domain.Carousel;
import com.ityueqiangu.project.carousel.mapper.CarouselMapper;
import com.ityueqiangu.project.carousel.service.ICarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author FlowerStone
 * @title: CarouselServiceImpl
 * @description:
 * @date 2021-11-19 11:51:44
 */
@Service
public class CarouselServiceImpl implements ICarouselService{

    @Autowired
    private CarouselMapper carouselMapper;

    /**
     * 查询分页记录
     *
     * @return 返回集合，没有返回空List
     */
     public List<Carousel> selectCarouselList(Carousel carousel) {
       return carouselMapper.selectCarouselList(carousel);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    public Carousel selectCarouselById(Integer id) {
    	return carouselMapper.selectCarouselById(id);
    }
	
    /**
     * 新增
     *
     * @param carousel 新增的记录
     * @return 返回ResultInfo
     */
    public Integer insertCarousel(Carousel carousel) {
        carousel.setIsDisplay(Constants.YES);
    	return carouselMapper.insertCarousel(carousel);
    }
	
	
    /**
     * 修改
     *
     * @param carousel 修改的记录
     * @return 返回
     */
    public Integer updateCarousel(Carousel carousel) {
    	return carouselMapper.updateCarousel(carousel);
    }
	
  
    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回
     */
    public Integer deleteCarouselById(Integer id) {
    	return carouselMapper.deleteCarouselById(id);
    }
	
}