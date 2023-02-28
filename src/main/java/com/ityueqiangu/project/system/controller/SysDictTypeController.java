package com.ityueqiangu.project.system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.RequestPath;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ityueqiangu.core.web.controller.BaseController;
import com.ityueqiangu.core.web.page.TableDataInfo;
import com.ityueqiangu.core.web.domain.ResponseInfo;

import com.ityueqiangu.project.system.domain.SysDictType;
import com.ityueqiangu.project.system.service.ISysDictTypeService;

/**
 * @author FlowerStone
 * @title: SysDictTypeController
 * @description:
 * @date 2022-03-21 17:06:28
 */
@Controller
@RequestMapping(value="/admin/sysDictType")
public class SysDictTypeController extends BaseController{

    @Autowired
    private ISysDictTypeService sysDictTypeService;
    
    @GetMapping(value = "/index")
    public String index(){
        return "system/dict/type/index";
    }

    @GetMapping(value = "/add")
    public String add(){
        return "system/dict/type/add";
    }

    @GetMapping(value = "/edit")
    public String edit(Integer id, ModelMap mmap){
        SysDictType sysDictType = sysDictTypeService.selectSysDictTypeById(id);
        mmap.addAttribute("sysDictType",sysDictType);
        return "system/dict/type/edit";
    }

    /**
     * 分页查询
     *
     * @return 分页数据
     */
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysDictType sysDictType) {
        startPage();
        List<SysDictType> list = sysDictTypeService.selectSysDictTypeList(sysDictType);
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
        return ResponseInfo.success(sysDictTypeService.selectSysDictTypeById(id));
    }    
     
    /**
     * 新增
     *
     * @param sysDictType 新增的记录
     * @return 返回影响行数
     */
    @PostMapping("/insert")
    @ResponseBody
    public ResponseInfo insert(@RequestBody SysDictType sysDictType) {
        return toAjax(sysDictTypeService.insertSysDictType(sysDictType));
    }    
      
    /**
     * 修改
     *
     * @param sysDictType 修改的记录
     * @return 返回影响行数
     */
    @PostMapping("/update")
    @ResponseBody
    public ResponseInfo update(@RequestBody SysDictType sysDictType) {
        return toAjax(sysDictTypeService.updateSysDictType(sysDictType));
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
        return toAjax(sysDictTypeService.deleteSysDictTypeById(id));
    }
    
}