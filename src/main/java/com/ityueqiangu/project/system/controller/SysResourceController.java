package com.ityueqiangu.project.system.controller;

import com.ityueqiangu.core.web.controller.BaseController;
import com.ityueqiangu.core.web.domain.Dtree;
import com.ityueqiangu.core.web.domain.ResponseInfo;
import com.ityueqiangu.core.web.page.TableDataInfo;
import com.ityueqiangu.project.system.domain.SysResource;
import com.ityueqiangu.project.system.service.ISysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FlowerStone
 * @title: SysResourceController
 * @description:
 * @date 2021-11-14 10:38:35
 */
@Controller
@RequestMapping(value = "/admin/sysResource")
public class SysResourceController extends BaseController {

    @Autowired
    private ISysResourceService sysResourceService;

    @GetMapping(value = "/index")
    public String index() {
        return "system/resource/index";
    }

    @GetMapping(value = "/add")
    public String add() {
        return "system/resource/add";
    }

    @GetMapping(value = "/edit")
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
    @GetMapping("/list")
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
        Dtree rootTree = new Dtree();
        rootTree.setId(1);
        rootTree.setParentId(0);
        rootTree.setTitle("顶级权限");
        dtrees.add(rootTree);
        sysResources.stream().forEach(param -> {
            Dtree dtree = new Dtree();
            dtree.setId(param.getId());
            dtree.setParentId(param.getParentId());
            dtree.setTitle(param.getResourceName());
            dtrees.add(dtree);
        });

        return new ResponseInfo(ResponseInfo.Type.ZERO.value(), "操作成功", dtrees);
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
        return ResponseInfo.success(sysResourceService.selectSysResourceById(id));
    }

    /**
     * 新增
     *
     * @param sysResource 新增的记录
     * @return 返回影响行数
     */
    @PostMapping("/insert")
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
    @PostMapping("/update")
    @ResponseBody
    public ResponseInfo update(@RequestBody SysResource sysResource) {
        return toAjax(sysResourceService.updateSysResource(sysResource));
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
        return toAjax(sysResourceService.deleteSysResourceById(id));
    }

    /**
     * 修改状态
     *
     * @param sysResource
     * @return
     * @author FlowerStone
     * @date 2021年11月19日 0019 15:22:04
     */
    @PostMapping(value = "/updateStatus")
    @ResponseBody
    public ResponseInfo updateStatus(SysResource sysResource) {
        return toAjax(sysResourceService.updateStatus(sysResource));
    }

}