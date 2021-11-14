package com.ityueqiangu.project.system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ityueqiangu.core.web.controller.BaseController;
import com.ityueqiangu.core.web.page.TableDataInfo;
import com.ityueqiangu.core.web.domain.ResponseInfo;

import com.ityueqiangu.project.system.domain.SysUserRole;
import com.ityueqiangu.project.system.service.ISysUserRoleService;

/**
 * @author FlowerStone
 * @title: SysUserRoleController
 * @description:
 * @date 2021-11-14 07:11:02
 */
@Controller
@RequestMapping(value="/sysUserRole")
public class SysUserRoleController extends BaseController{

    @Autowired
    private ISysUserRoleService sysUserRoleService;
    
    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/add")
    public String add(){
        return "add";
    }

    @RequestMapping(value = "/edit")
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
    @RequestMapping("/list")
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
    @RequestMapping("/getById")
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
    @RequestMapping("/insert")
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
    @RequestMapping("/update")
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
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseInfo delete(@RequestBody Integer id) {
        return toAjax(sysUserRoleService.deleteSysUserRoleById(id));
    }
    
}