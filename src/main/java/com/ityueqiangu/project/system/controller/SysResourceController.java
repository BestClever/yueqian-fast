package com.ityueqiangu.project.system.controller;

import java.util.ArrayList;
import java.util.List;

import com.ityueqiangu.core.web.domain.Dtree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ityueqiangu.core.web.controller.BaseController;
import com.ityueqiangu.core.web.page.TableDataInfo;
import com.ityueqiangu.core.web.domain.ResponseInfo;

import com.ityueqiangu.project.system.domain.SysResource;
import com.ityueqiangu.project.system.service.ISysResourceService;

/**
 * @author FlowerStone
 * @title: SysResourceController
 * @description:
 * @date 2021-11-14 10:38:35
 */
@Controller
@RequestMapping(value = "/sysResource")
public class SysResourceController extends BaseController {

    @Autowired
    private ISysResourceService sysResourceService;

    @RequestMapping(value = "/index")
    public String index() {
        return "system/resource/index";
    }

    @RequestMapping(value = "/add")
    public String add() {
        return "system/resource/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(Integer id, ModelMap mmap) {
        SysResource sysResource = sysResourceService.selectSysResourceById(id);
        mmap.addAttribute("sysResource", sysResource);
        return "system/resource/edit";
    }

    /**
     * 分页查询
     *
     * @return 分页数据
     */
    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysResource sysResource) {
        startPage();
        List<SysResource> list = sysResourceService.selectSysResourceList(sysResource);
        return getDataTable(list);
    }

    /**
     * 获取父级菜单
     *
     * @param sysResource
     * @return
     * @author FlowerStone
     * @date 2021年11月14日 0014 14:24:29
     */
    @GetMapping(value = "/selectParent")
    @ResponseBody
    public ResponseInfo selectParent(SysResource sysResource) {
        List<SysResource> sysResources = sysResourceService.selectSysResourceList(sysResource);
        ArrayList<Dtree> dtrees = new ArrayList<>();
        sysResources.stream().forEach(param -> {
            Dtree dtree = new Dtree();
            dtree.setId(param.getId());
            dtree.setParentId(param.getParentId());
            dtree.setTitle(param.getResourceName());
            dtrees.add(dtree);
        });
        return new ResponseInfo(ResponseInfo.Type.ZERO,"操作成功",dtrees);
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
        return ResponseInfo.success(sysResourceService.selectSysResourceById(id));
    }

    /**
     * 新增
     *
     * @param sysResource 新增的记录
     * @return 返回影响行数
     */
    @RequestMapping("/insert")
    @ResponseBody
    public ResponseInfo insert(@RequestBody SysResource sysResource) {
        return toAjax(sysResourceService.insertSysResource(sysResource));
    }

    /**
     * 修改
     *
     * @param sysResource 修改的记录
     * @return 返回影响行数
     */
    @RequestMapping("/update")
    @ResponseBody
    public ResponseInfo update(SysResource sysResource) {
        return toAjax(sysResourceService.updateSysResource(sysResource));
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
        return toAjax(sysResourceService.deleteSysResourceById(id));
    }

}