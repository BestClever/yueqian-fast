package com.ityueqiangu.system.controller;


import cn.hutool.core.util.ObjectUtil;
import com.ityueqiangu.core.constant.Constants;
import com.ityueqiangu.core.enums.CommonEnum;
import com.ityueqiangu.core.web.controller.BaseController;
import com.ityueqiangu.core.web.result.DataGridResultInfo;
import com.ityueqiangu.core.web.result.ResultDataUtil;
import com.ityueqiangu.core.web.result.ResultInfo;
import com.ityueqiangu.system.domain.SysRole;
import com.ityueqiangu.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Clever、xia
 * @title: SysRoleController
 * @description:
 * @date 2021-04-27 16:58:27
 */
@Controller
@RequestMapping(value = "/sysRole")
public class SysRoleController extends BaseController {

    @Autowired
    private ISysRoleService sysRoleService;
    /*访问路径前缀*/
    private final String PATH_PREFIX = "system/role/";

    /**
     * 访问主页
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String index() {
        return PATH_PREFIX + "index";
    }

    /**
     * 新增
     *
     * @return
     */
    @RequestMapping(value = "/add")
    public String add() {
        return PATH_PREFIX + "add";
    }

    /**
     * 修改
     *
     * @return
     */
    @RequestMapping(value = "/edit")
    public String edit() {
        return PATH_PREFIX + "edit";
    }

    /**
     * 跳转到 授权页面
     *
     * @return
     */
    @RequestMapping(value = "/authorization")
    public String authorization(Integer id, ModelMap mmap) {
        mmap.put("roleId",id);
        return PATH_PREFIX + "permissionselect";
    }

    /**
     * 分页列表查询
     *
     * @param sysRole
     * @return 返回DataGridResultInfo
     */
    @RequestMapping("/list")
    @ResponseBody
    public DataGridResultInfo list(SysRole sysRole) {
        startPage();
        List<SysRole> list = sysRoleService.selectSysRoleList(sysRole);
        return getDataTable(list);
    }

    @RequestMapping("/listRole")
    @ResponseBody
    public ResultInfo listRole(Integer userId) {
        List<SysRole> list = sysRoleService.listRole(userId);
        return ResultDataUtil.createSuccess(CommonEnum.LAYUI_SUCCESS).setData(list);
    }




    /**
     * 根据主键查询
     *
     * @param sysRole
     * @return 返回ResultInfo
     */
    @RequestMapping("/selectSysRoleById")
    @ResponseBody
    public ResultInfo selectSysRoleById(SysRole sysRole) {
        SysRole sysRoleResult = sysRoleService.selectSysRoleById(sysRole.getId());
        return ResultDataUtil.createSuccess(CommonEnum.SELECT_SUCCESS).setData(sysRoleResult);
    }

    /**
     * 新增
     *
     * @param sysRole 新增的记录
     * @return 返回ResultInfo
     */
    @RequestMapping("/save")
    @ResponseBody
    public ResultInfo save(SysRole sysRole) {
        Integer result = sysRoleService.insertSysRole(sysRole);
        if (result < Constants.ONE) {
            ResultDataUtil.createFail(CommonEnum.SAVE_FAILURE);
        }
        return ResultDataUtil.createSuccess(CommonEnum.SAVE_SUCCESS);
    }


    /**
     * 修改，
     *
     * @param sysRole 修改的记录
     * @return 返回ResultInfo
     */
    @RequestMapping("/update")
    @ResponseBody
    public ResultInfo update(SysRole sysRole) {
        Integer result = sysRoleService.updateSysRole(sysRole);
        if (result < Constants.ONE) {
            ResultDataUtil.createFail(CommonEnum.UPDATE_FAILURE);
        }
        return ResultDataUtil.createSuccess(CommonEnum.UPDATE_SUCCESS);
    }

    /**
     * 删除
     *
     * @param sysRole 待删除的记录
     * @return 返回ResultInfo
     */
    @RequestMapping("/remove")
    @ResponseBody
    public ResultInfo remove(SysRole sysRole) {
        Integer result = sysRoleService.deleteSysRoleById(sysRole.getId());
        if (result < Constants.ONE) {
            ResultDataUtil.createFail(CommonEnum.DELETE_FAILURE);
        }
        return ResultDataUtil.createSuccess(CommonEnum.DELETE_SUCCESS);
    }

    /**
     * 查询角色是否存在
     *
     * @param sysRole
     * @return
     */
    @RequestMapping(value = "/existRole")
    @ResponseBody
    public ResultInfo existRole(SysRole sysRole) {
        SysRole result = sysRoleService.existRole(sysRole);
        if (ObjectUtil.isNotNull(result)) {
            return ResultDataUtil.createFail(CommonEnum.DATA_EXIST);
        }
        return ResultDataUtil.createSuccess(CommonEnum.DATA_NOT_EXIST);
    }
}