package com.ityueqiangu.project.system.controller;

import com.ityueqiangu.core.web.controller.BaseController;
import com.ityueqiangu.core.web.domain.ResponseInfo;
import com.ityueqiangu.core.web.page.TableDataInfo;
import com.ityueqiangu.project.system.domain.SysRoleResource;
import com.ityueqiangu.project.system.service.ISysRoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author FlowerStone
 * @title: SysRoleResourceController
 * @description:
 * @date 2021-11-14 07:11:02
 */
@Controller
@RequestMapping(value="/admin/sysRoleResource")
public class SysRoleResourceController extends BaseController {

    @Autowired
    private ISysRoleResourceService sysRoleResourceService;
    
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
        SysRoleResource sysRoleResource = sysRoleResourceService.selectSysRoleResourceById(id);
        mmap.addAttribute("sysRoleResource",sysRoleResource);
        return "edit";
    }

    /**
     * 分页查询
     *
     * @return 分页数据
     */
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysRoleResource sysRoleResource) {
        startPage();
        List<SysRoleResource> list = sysRoleResourceService.selectSysRoleResourceList(sysRoleResource);
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
        return ResponseInfo.success(sysRoleResourceService.selectSysRoleResourceById(id));
    }    
     
    /**
     * 新增
     *
     * @param sysRoleResource 新增的记录
     * @return 返回影响行数
     */
    @PostMapping("/insert")
    @ResponseBody
    public ResponseInfo insert(@RequestBody SysRoleResource sysRoleResource) {
        return toAjax(sysRoleResourceService.insertSysRoleResource(sysRoleResource));
    }    
      
    /**
     * 修改
     *
     * @param sysRoleResource 修改的记录
     * @return 返回影响行数
     */
    @PostMapping("/update")
    @ResponseBody
    public ResponseInfo update(SysRoleResource sysRoleResource) {
        return toAjax(sysRoleResourceService.updateSysRoleResource(sysRoleResource));
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
        return toAjax(sysRoleResourceService.deleteSysRoleResourceById(id));
    }

    /**
     * 保存角色资源状态
     *
     * @return
     * @author FlowerStone
     * @date 2021年11月15日 0015 20:49:34
     */
    @PostMapping(value = "/saveRoleResource")
    @ResponseBody
    public ResponseInfo saveRoleResource(SysRoleResource sysRoleResource) {
        sysRoleResourceService.saveRoleResource(sysRoleResource);
        return ResponseInfo.success();
    }
}