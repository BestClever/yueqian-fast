package com.ityueqiangu.project.system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.ityueqiangu.core.web.controller.BaseController;
import com.ityueqiangu.core.web.page.TableDataInfo;
import com.ityueqiangu.core.web.domain.ResponseInfo;

import com.ityueqiangu.project.system.domain.SysUser;
import com.ityueqiangu.project.system.service.ISysUserService;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/sysUser")
public class SysUserController extends BaseController{

    @Autowired
    private ISysUserService sysUserService;

    @RequestMapping(value = "/index")
    public String main(){
        return "system/user/index";
    }

    @RequestMapping(value = "/add")
    public String add(){
        return "system/user/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(){
        return "system/user/edit";
    }

    /**
     * 分页查询
     *
     * @return 分页数据
     */
    @RequestMapping("list")
    @ResponseBody
    public TableDataInfo list(SysUser sysUser) {
        startPage();
        List<SysUser> list = sysUserService.selectSysUserList(sysUser);
        return getDataTable(list);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @RequestMapping("getById")
    public ResponseInfo getById(String id) {
        return ResponseInfo.success(sysUserService.selectSysUserById(id));
    }

    /**
     * 新增
     *
     * @param sysUser 新增的记录
     * @return 返回影响行数
     */
    @RequestMapping("insert")
    public ResponseInfo insert(@RequestBody SysUser sysUser) {
        return toAjax(sysUserService.insertSysUser(sysUser));
    }

    /**
     * 修改
     *
     * @param sysUser 修改的记录
     * @return 返回影响行数
     */
    @RequestMapping("update")
    public ResponseInfo update(@RequestBody SysUser sysUser) {
        return toAjax(sysUserService.updateSysUser(sysUser));
    }

    /**
     * 删除记录
     *
     * @param id
     * @return 返回影响行数
     */
    @RequestMapping("delete")
    public ResponseInfo delete(@RequestBody String id) {
        return toAjax(sysUserService.deleteSysUserById(id));
    }

}