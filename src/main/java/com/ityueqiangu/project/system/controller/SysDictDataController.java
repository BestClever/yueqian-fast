package com.ityueqiangu.project.system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ityueqiangu.core.web.controller.BaseController;
import com.ityueqiangu.core.web.page.TableDataInfo;
import com.ityueqiangu.core.web.domain.ResponseInfo;

import com.ityueqiangu.project.system.domain.SysDictData;
import com.ityueqiangu.project.system.service.ISysDictDataService;

/**
 * @author FlowerStone
 * @title: SysDictDataController
 * @description:
 * @date 2022-03-21 16:22:42
 */
@Controller
@RequestMapping(value="/admin/sysDictData")
public class SysDictDataController extends BaseController{

    @Autowired
    private ISysDictDataService sysDictDataService;
    
    @GetMapping(value = "/index/{dictType}")
    public String index(ModelMap mmp,@PathVariable(value = "dictType") String dictType){
        mmp.addAttribute("dictType",dictType);
        return "system/dict/data/index";
    }

    @GetMapping(value = "/add")
    public String add(){
        return "system/dict/data/add";
    }

    @GetMapping(value = "/edit")
    public String edit(Integer id, ModelMap mmap){
        SysDictData sysDictData = sysDictDataService.selectSysDictDataById(id);
        mmap.addAttribute("sysDictData",sysDictData);
        return "system/dict/data/edit";
    }

    /**
     * 分页查询
     *
     * @return 分页数据
     */
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysDictData sysDictData) {
        startPage();
        List<SysDictData> list = sysDictDataService.selectSysDictDataList(sysDictData);
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
        return ResponseInfo.success(sysDictDataService.selectSysDictDataById(id));
    }    
     
    /**
     * 新增
     *
     * @param sysDictData 新增的记录
     * @return 返回影响行数
     */
    @PostMapping("/insert")
    @ResponseBody
    public ResponseInfo insert(@RequestBody SysDictData sysDictData) {
        return toAjax(sysDictDataService.insertSysDictData(sysDictData));
    }    
      
    /**
     * 修改
     *
     * @param sysDictData 修改的记录
     * @return 返回影响行数
     */
    @PostMapping("/update")
    @ResponseBody
    public ResponseInfo update(@RequestBody SysDictData sysDictData) {
        return toAjax(sysDictDataService.updateSysDictData(sysDictData));
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
        return toAjax(sysDictDataService.deleteSysDictDataById(id));
    }
    
}