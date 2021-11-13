package com.ityueqiangu.project.system.controller;

import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ityueqiangu.core.web.controller.BaseController;
import com.ityueqiangu.core.web.page.TableDataInfo;
import com.ityueqiangu.core.web.domain.ResponseInfo;

import com.ityueqiangu.project.system.domain.SysRole;
import com.ityueqiangu.project.system.service.ISysRoleService;

/**
 * @author FlowerStone
 * @title: SysRoleController
 * @description:
 * @date 2021-11-13 14:05:24
 */
@Controller
@RequestMapping(value = "/sysRole")
public class SysRoleController extends BaseController {

    @Autowired
    private ISysRoleService sysRoleService;

    @RequestMapping(value = "/index")
    public String index() {
        return "system/role/index";
    }

    @RequestMapping(value = "/add")
    public String add() {
        return "system/role/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(Integer id, ModelMap mmap) {
        SysRole sysRole = sysRoleService.selectSysRoleById(id);
        mmap.addAttribute("sysRole", sysRole);
        return "system/role/edit";
    }

    /**
     * 分页查询
     *
     * @return 分页数据
     */
    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysRole sysRole) {
        startPage();
        List<SysRole> list = sysRoleService.selectSysRoleList(sysRole);
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
        return ResponseInfo.success(sysRoleService.selectSysRoleById(id));
    }

    /**
     * 新增
     *
     * @param sysRole 新增的记录
     * @return 返回影响行数
     */
    @RequestMapping("/insert")
    @ResponseBody
    public ResponseInfo insert(@RequestBody SysRole sysRole) {
        return toAjax(sysRoleService.insertSysRole(sysRole));
    }

    /**
     * 修改
     *
     * @param sysRole 修改的记录
     * @return 返回影响行数
     */
    @RequestMapping("/update")
    @ResponseBody
    public ResponseInfo update(@RequestBody SysRole sysRole) {
        return toAjax(sysRoleService.updateSysRole(sysRole));
    }

    /**
     * 删除记录
     *
     * @param id
     * @return 返回影响行数
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseInfo delete(Integer id) {
        return toAjax(sysRoleService.deleteSysRoleById(id));
    }


    /**
     * 判断是否存在
     *
     * @param sysRole
     * @return
     * @author FlowerStone
     * @date 2021年11月13日 0013 20:54:44
     */
    @GetMapping(value = "/existRoleCode")
    @ResponseBody
    public ResponseInfo existRoleCode(SysRole sysRole) {
        SysRole result = sysRoleService.getOne(sysRole);
        if (ObjectUtil.isEmpty(result)) {
            return ResponseInfo.success();
        } else {
            return ResponseInfo.error("编码已经存在！");
        }
    }

    /**
     * 更新状态
     *
     * @param sysRole
     * @return
     * @author FlowerStone
     * @date 2021年11月13日 0013 21:57:47
     */
    @ResponseBody
    @PostMapping(value = "/updateStatus")
    public ResponseInfo updateStatus(SysRole sysRole) {
        sysRoleService.updateSysRole(sysRole);
        return ResponseInfo.success("更新成功");
    }
}