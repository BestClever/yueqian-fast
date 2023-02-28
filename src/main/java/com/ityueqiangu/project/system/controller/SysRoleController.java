package com.ityueqiangu.project.system.controller;

import cn.hutool.core.util.ObjectUtil;
import com.ityueqiangu.core.web.controller.BaseController;
import com.ityueqiangu.core.web.domain.Dtree;
import com.ityueqiangu.core.web.domain.ResponseInfo;
import com.ityueqiangu.core.web.page.TableDataInfo;
import com.ityueqiangu.project.system.domain.SysRole;
import com.ityueqiangu.project.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author FlowerStone
 * @title: SysRoleController
 * @description:
 * @date 2021-11-13 14:05:24
 */
@Controller
@RequestMapping(value = "/admin/sysRole")
public class SysRoleController extends BaseController {

    @Autowired
    private ISysRoleService sysRoleService;

    @GetMapping(value = "/index")
    public String index() {
        return "system/role/index";
    }

    @GetMapping(value = "/add")
    public String add() {
        return "system/role/add";
    }

    @GetMapping(value = "/edit")
    public String edit(Integer id, ModelMap mmap) {
        SysRole sysRole = sysRoleService.selectSysRoleById(id);
        mmap.addAttribute("sysRole", sysRole);
        return "system/role/edit";
    }

    /**
     * 跳转到资源选中列表中
     *
     * @param id
     * @param mmap
     * @return
     * @author FlowerStone
     * @date 2021年11月15日 0015 15:46:46
     */
    @GetMapping(value = "/authorization")
    public String authorization(Integer id, ModelMap mmap) {
        mmap.addAttribute("roleId", id);
        return "system/role/resource";
    }

    /**
     * 分页查询
     *
     * @return 分页数据
     */
    @GetMapping("/list")
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
    @GetMapping("/getById")
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
    @PostMapping("/insert")
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
    @PostMapping("/update")
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
    @GetMapping("/delete")
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

    /**
     * 获取角色分配的资源
     *
     * @param roleId
     * @return
     * @author FlowerStone
     * @date 2021年11月15日 0015 15:00:50
     */
    @GetMapping(value = "/getRoleResource")
    @ResponseBody
    public ResponseInfo getRoleResource(Integer roleId) {
        List<Dtree> dtrees = sysRoleService.getRoleResource(roleId);
        return new ResponseInfo(ResponseInfo.Type.ZERO.value(), "操作成功", dtrees);
    }

}