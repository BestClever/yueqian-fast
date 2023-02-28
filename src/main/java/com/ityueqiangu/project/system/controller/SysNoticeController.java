package com.ityueqiangu.project.system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ityueqiangu.core.web.controller.BaseController;
import com.ityueqiangu.core.web.page.TableDataInfo;
import com.ityueqiangu.core.web.domain.ResponseInfo;

import com.ityueqiangu.project.system.domain.SysNotice;
import com.ityueqiangu.project.system.service.ISysNoticeService;

/**
 * @author FlowerStone
 * @title: SysNoticeController
 * @description:
 * @date 2022-03-18 22:09:31
 */
@Controller
@RequestMapping(value="/sysNotice")
public class SysNoticeController extends BaseController{

    @Autowired
    private ISysNoticeService sysNoticeService;
    
    @GetMapping(value = "/index")
    public String index(){
        return "index";
    }

    @GetMapping(value = "/add")
    public String add(){
        return "add";
    }

    @GetMapping(value = "/edit")
    public String edit(Integer id, ModelMap mmap){
        SysNotice sysNotice = sysNoticeService.selectSysNoticeById(id);
        mmap.addAttribute("sysNotice",sysNotice);
        return "edit";
    }

    /**
     * 分页查询
     *
     * @return 分页数据
     */
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysNotice sysNotice) {
        startPage();
        List<SysNotice> list = sysNoticeService.selectSysNoticeList(sysNotice);
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
        return ResponseInfo.success(sysNoticeService.selectSysNoticeById(id));
    }    
     
    /**
     * 新增
     *
     * @param sysNotice 新增的记录
     * @return 返回影响行数
     */
    @PostMapping("/insert")
    @ResponseBody
    public ResponseInfo insert(@RequestBody SysNotice sysNotice) {
        return toAjax(sysNoticeService.insertSysNotice(sysNotice));
    }    
      
    /**
     * 修改
     *
     * @param sysNotice 修改的记录
     * @return 返回影响行数
     */
    @PostMapping("/update")
    @ResponseBody
    public ResponseInfo update(@RequestBody SysNotice sysNotice) {
        return toAjax(sysNoticeService.updateSysNotice(sysNotice));
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
        return toAjax(sysNoticeService.deleteSysNoticeById(id));
    }
    
}