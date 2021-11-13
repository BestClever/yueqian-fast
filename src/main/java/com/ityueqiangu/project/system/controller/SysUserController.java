package com.ityueqiangu.project.system.controller;

import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    public String edit(Integer id, ModelMap mmap){
        SysUser sysUser = sysUserService.selectSysUserById(id);
        mmap.addAttribute("sysUser",sysUser);
        return "system/user/edit";
    }

    /**
     * 分页查询
     *
     * @return 分页数据
     */
    @RequestMapping("/list")
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
    @RequestMapping("/getById")
    @ResponseBody
    public ResponseInfo getById(Integer id) {
        return ResponseInfo.success(sysUserService.selectSysUserById(id));
    }

    /**
     * 新增
     *
     * @param sysUser 新增的记录
     * @return 返回影响行数
     */
    @RequestMapping("/insert")
    @ResponseBody
    public ResponseInfo insert(@RequestBody SysUser sysUser) {
        return toAjax(sysUserService.insertSysUser(sysUser));
    }

    /**
     * 修改
     *
     * @param sysUser 修改的记录
     * @return 返回影响行数
     */
    @RequestMapping("/update")
    @ResponseBody
    public ResponseInfo update(@RequestBody SysUser sysUser) {
        return toAjax(sysUserService.updateSysUser(sysUser));
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
        return toAjax(sysUserService.deleteSysUserById(id));
    }

    /**
     * 判断用户是否存在
     * @author FlowerStone
     * @date 2021年11月12日 0012 21:04:07
     * @param sysUser
     * @return
     */
    @RequestMapping(value = "/existUser")
    @ResponseBody
    public ResponseInfo existUser(SysUser sysUser){
        SysUser result = sysUserService.getOne(sysUser);
        if (ObjectUtil.isEmpty(result)) {
            return ResponseInfo.success("用户不存在");
        }else {
            return ResponseInfo.error("用户存在");
        }
    }

}