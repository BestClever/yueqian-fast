package com.ityueqiangu.project.system.controller;

import com.ityueqiangu.core.web.controller.BaseController;
import com.ityueqiangu.core.web.domain.ResponseInfo;
import com.ityueqiangu.core.web.page.TableDataInfo;
import com.ityueqiangu.project.system.domain.SysUserRole;
import com.ityueqiangu.project.system.service.ISysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author FlowerStone
 * @title: SysUserRoleController
 * @description:
 * @date 2021-11-14 07:11:02
 */
@Controller
@RequestMapping(value="/admin/sysUserRole")
public class SysUserRoleController extends BaseController {

    @Autowired
    private ISysUserRoleService sysUserRoleService;
    
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
        SysUserRole sysUserRole = sysUserRoleService.selectSysUserRoleById(id);
        mmap.addAttribute("sysUserRole",sysUserRole);
        return "edit";
    }

    /**
     * 分页查询
     *
     * @return 分页数据
     */
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysUserRole sysUserRole) {
        startPage();
        List<SysUserRole> list = sysUserRoleService.selectSysUserRoleList(sysUserRole);
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
        return ResponseInfo.success(sysUserRoleService.selectSysUserRoleById(id));
    }    
     
    /**
     * 新增
     *
     * @param sysUserRole 新增的记录
     * @return 返回影响行数
     */
    @PostMapping("/insert")
    @ResponseBody
    public ResponseInfo insert(@RequestBody SysUserRole sysUserRole) {
        return toAjax(sysUserRoleService.insertSysUserRole(sysUserRole));
    }    
      
    /**
     * 修改
     *
     * @param sysUserRole 修改的记录
     * @return 返回影响行数
     */
    @PostMapping("/update")
    @ResponseBody
    public ResponseInfo update(SysUserRole sysUserRole) {
        return toAjax(sysUserRoleService.updateSysUserRole(sysUserRole));
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
        return toAjax(sysUserRoleService.deleteSysUserRoleById(id));
    }
    
}