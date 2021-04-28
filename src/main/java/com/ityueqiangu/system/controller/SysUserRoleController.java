package com.ityueqiangu.system.controller;


import com.ityueqiangu.core.web.controller.BaseController;
import com.ityueqiangu.core.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ityueqiangu.core.web.result.DataGridResultInfo;
import com.ityueqiangu.core.web.result.ResultDataUtil;
import com.ityueqiangu.core.web.result.ResultInfo;
import org.springframework.stereotype.Controller;
import com.ityueqiangu.core.enums.CommonEnum;
import java.util.List;
import com.ityueqiangu.system.domain.SysUserRole;
import com.ityueqiangu.system.service.ISysUserRoleService;

/**
 * @author Clever、xia
 * @title: SysUserRoleController
 * @description:
 * @date 2021-04-27 16:58:27
 */
@Controller
@RequestMapping(value = "/sysUserRole")
public class SysUserRoleController extends BaseController{

    @Autowired
    private ISysUserRoleService sysUserRoleService;
	/*访问路径前缀*/
    private final String PATH_PREFIX = "sysUserRole/";

	 /**
     * 访问主页
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(){
        return PATH_PREFIX+"index";
    }

    /**
     * 新增
     * @return
     */
    @RequestMapping(value = "/add")
    public String add(){
        return PATH_PREFIX+"add";
    }

    /**
     * 修改
     * @return
     */
    @RequestMapping(value = "/edit")
    public String edit(){
        return PATH_PREFIX+"edit";
    }
	
    /**
     * 分页列表查询
     * @param sysUserRole
     * @return 返回DataGridResultInfo
     */
    @RequestMapping("/list")
    @ResponseBody
    public DataGridResultInfo list(SysUserRole sysUserRole) {
    	startPage();
        List<SysUserRole> list = sysUserRoleService.selectSysUserRoleList(sysUserRole);
        return getDataTable(list);
    }


    /**
     * 根据主键查询
     *
     * @param sysUserRole
     * @return 返回ResultInfo
     */
    @RequestMapping("/selectSysUserRoleById")
    @ResponseBody
    public ResultInfo selectSysUserRoleById(SysUserRole sysUserRole) {
    	SysUserRole sysUserRoleResult = sysUserRoleService.selectSysUserRoleById(sysUserRole.getId());
        return ResultDataUtil.createSuccess(CommonEnum.SELECT_SUCCESS).setData(sysUserRoleResult);
    }
    
    /**
     * 新增
     *
     * @param sysUserRole 新增的记录
     * @return 返回ResultInfo
     */
    @RequestMapping("/save")
    @ResponseBody
    public ResultInfo save(SysUserRole sysUserRole) {
        Integer result = sysUserRoleService.insertSysUserRole(sysUserRole);
        if (result< Constants.ONE) {
            ResultDataUtil.createFail(CommonEnum.SAVE_FAILURE);
        }
        return ResultDataUtil.createSuccess(CommonEnum.SAVE_SUCCESS);
    }
    
    
    /**
     * 修改，
     *
     * @param sysUserRole 修改的记录
     * @return 返回ResultInfo
     */
    @RequestMapping("/update")
    @ResponseBody
    public ResultInfo update(SysUserRole sysUserRole) {
    	Integer result = sysUserRoleService.updateSysUserRole(sysUserRole);
        if (result< Constants.ONE) {
            ResultDataUtil.createFail(CommonEnum.UPDATE_FAILURE);
        }
        return ResultDataUtil.createSuccess(CommonEnum.UPDATE_SUCCESS);
    }
    
    /**
     * 删除
     *
     * @param sysUserRole 待删除的记录
     * @return 返回ResultInfo
     */
    @RequestMapping("/remove")
    @ResponseBody
    public ResultInfo remove(SysUserRole sysUserRole) {
    	Integer result = sysUserRoleService.deleteSysUserRoleById(sysUserRole.getId());
        if (result< Constants.ONE) {
            ResultDataUtil.createFail(CommonEnum.DELETE_FAILURE);
        }
        return ResultDataUtil.createSuccess(CommonEnum.DELETE_SUCCESS);
    } 
}