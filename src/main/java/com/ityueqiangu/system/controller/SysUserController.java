package com.ityueqiangu.system.controller;


import cn.hutool.core.util.ObjectUtil;
import com.ityueqiangu.core.constant.Constants;
import com.ityueqiangu.core.enums.CommonEnum;
import com.ityueqiangu.core.web.controller.BaseController;
import com.ityueqiangu.core.web.result.DataGridResultInfo;
import com.ityueqiangu.core.web.result.ResultDataUtil;
import com.ityueqiangu.core.web.result.ResultInfo;
import com.ityueqiangu.system.domain.SysUser;
import com.ityueqiangu.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Clever、xia
 * @title: SysUserController
 * @description:
 * @date 2021-04-03 19:52:56
 */
@Controller
@RequestMapping(value = "/sysUser")
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;
	/*访问路径前缀*/
    private final String PATH_PREFIX = "system/";

	 /**
     * 访问主页
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(){
        return PATH_PREFIX+"user/index";
    }

    /**
     * 新增
     * @return
     */
    @RequestMapping(value = "/add")
    public String add(){
        return PATH_PREFIX+"user/add";
    }

    /**
     * 修改
     * @return
     */
    @RequestMapping(value = "/edit")
    public String edit(){
        return PATH_PREFIX+"user/edit";
    }
	
    /**
     * 分页列表查询
     * @param sysUser
     * @return 返回DataGridResultInfo
     */
    @RequestMapping("/list")
    @ResponseBody
    public DataGridResultInfo list(SysUser sysUser) {
    	startPage();
        List<SysUser> list = sysUserService.selectSysUserList(sysUser);
        return getDataTable(list);
    }


    /**
     * 根据主键查询
     *
     * @param sysUser
     * @return 返回ResultInfo
     */
    @RequestMapping("/selectSysUserById")
    @ResponseBody
    public ResultInfo selectSysUserById(SysUser sysUser) {
    	SysUser sysUserResult = sysUserService.selectSysUserById(sysUser.getId());
        return ResultDataUtil.createSuccess(CommonEnum.SELECT_SUCCESS).setData(sysUserResult);
    }
    
    /**
     * 新增
     *
     * @param sysUser 新增的记录
     * @return 返回ResultInfo
     */
    @RequestMapping("/save")
    @ResponseBody
    public ResultInfo save(SysUser sysUser) {
        Integer result = sysUserService.insertSysUser(sysUser);
        if (result< Constants.ONE) {
            ResultDataUtil.createFail(CommonEnum.SAVE_FAILURE);
        }
        return ResultDataUtil.createSuccess(CommonEnum.SAVE_SUCCESS);
    }
    
    
    /**
     * 修改，
     *
     * @param sysUser 修改的记录
     * @return 返回ResultInfo
     */
    @RequestMapping("/update")
    @ResponseBody
    public ResultInfo update(SysUser sysUser) {
    	Integer result = sysUserService.updateSysUser(sysUser);
        if (result< Constants.ONE) {
            ResultDataUtil.createFail(CommonEnum.UPDATE_FAILURE);
        }
        return ResultDataUtil.createSuccess(CommonEnum.UPDATE_SUCCESS);
    }
    
    /**
     * 删除
     *
     * @param sysUser 待删除的记录
     * @return 返回ResultInfo
     */
    @RequestMapping("/remove")
    @ResponseBody
    public ResultInfo remove(SysUser sysUser) {
    	Integer result = sysUserService.deleteSysUserById(sysUser.getId());
        if (result< Constants.ONE) {
            ResultDataUtil.createFail(CommonEnum.DELETE_FAILURE);
        }
        return ResultDataUtil.createSuccess(CommonEnum.DELETE_SUCCESS);
    }

    /**
     * 查询登录名称是否存在
     * @param sysUser
     * @return
     */
    @RequestMapping("/existLoginName")
    @ResponseBody
    public ResultInfo existLoginName(SysUser sysUser){
       SysUser result = sysUserService.selectSysUserByLoginName(sysUser);
        if (ObjectUtil.isNotNull(result)) {
            return ResultDataUtil.createFail(CommonEnum.DATA_EXIST);
        }
        return ResultDataUtil.createSuccess(CommonEnum.DATA_NOT_EXIST);
    }
}