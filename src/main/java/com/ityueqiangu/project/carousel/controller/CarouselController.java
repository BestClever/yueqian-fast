package com.ityueqiangu.project.carousel.controller;

import com.ityueqiangu.core.web.controller.BaseController;
import com.ityueqiangu.core.web.domain.ResponseInfo;
import com.ityueqiangu.core.web.page.TableDataInfo;
import com.ityueqiangu.project.carousel.domain.Carousel;
import com.ityueqiangu.project.carousel.service.ICarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author FlowerStone
 * @title: CarouselController
 * @description:
 * @date 2021-11-19 11:51:44
 */
@Controller
@RequestMapping(value="/admin/carousel")
public class CarouselController extends BaseController {

    @Autowired
    private ICarouselService carouselService;
    
    @GetMapping(value = "/index")
    public String index(){
        return "carousel/index";
    }

    @GetMapping(value = "/add")
    public String add(){
        return "carousel/add";
    }

    @GetMapping(value = "/edit")
    public String edit(Integer id, ModelMap mmap){
        Carousel carousel = carouselService.selectCarouselById(id);
        mmap.addAttribute("carousel",carousel);
        return "carousel/edit";
    }

    /**
     * 分页查询
     *
     * @return 分页数据
     */
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Carousel carousel) {
        startPage();
        List<Carousel> list = carouselService.selectCarouselList(carousel);
        return getDataTable(list);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @GetMapping("/getById")
    @ResponseBody
    public ResponseInfo getById(Integer id) {
        return ResponseInfo.success(carouselService.selectCarouselById(id));
    }    
     
    /**
     * 新增
     *
     * @param carousel 新增的记录
     * @return 返回影响行数
     */
    @PostMapping("/insert")
    @ResponseBody
    public ResponseInfo insert(@RequestBody Carousel carousel) {
        return toAjax(carouselService.insertCarousel(carousel));
    }    
      
    /**
     * 修改
     *
     * @param carousel 修改的记录
     * @return 返回影响行数
     */
    @PostMapping("/update")
    @ResponseBody
    public ResponseInfo update(@RequestBody Carousel carousel) {
        return toAjax(carouselService.updateCarousel(carousel));
    }
    
    /**
     * 删除记录
     *
     * @param id
     * @return 返回影响行数
     */
    @PostMapping("/delete")
    @ResponseBody
    public ResponseInfo delete(Integer id) {
        return toAjax(carouselService.deleteCarouselById(id));
    }
    
}