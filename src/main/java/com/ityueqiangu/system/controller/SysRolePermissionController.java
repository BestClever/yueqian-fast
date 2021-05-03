package com.ityueqiangu.system.controller;


import com.ityueqiangu.core.web.controller.BaseController;
import com.ityueqiangu.core.constant.Constants;
import com.ityueqiangu.system.domain.SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ityueqiangu.core.web.result.DataGridResultInfo;
import com.ityueqiangu.core.web.result.ResultDataUtil;
import com.ityueqiangu.core.web.result.ResultInfo;
import org.springframework.stereotype.Controller;
import com.ityueqiangu.core.enums.CommonEnum;
import java.util.List;
import com.ityueqiangu.system.domain.SysRolePermission;
import com.ityueqiangu.system.service.ISysRolePermissionService;

/**
 * @author Clever、xia
 * @title: SysRolePermissionController
 * @description:
 * @date 2021-04-27 16:58:27
 */
@Controller
@RequestMapping(value = "/sysRolePermission")
public class SysRolePermissionController extends BaseController{

    @Autowired
    private ISysRolePermissionService sysRolePermissionService;
	/*访问路径前缀*/
    private final String PATH_PREFIX = "sysRolePermission/";

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
     * @param sysRolePermission
     * @return 返回DataGridResultInfo
     */
    @RequestMapping("/list")
    @ResponseBody
    public DataGridResultInfo list(SysRolePermission sysRolePermission) {
    	startPage();
        List<SysRolePermission> list = sysRolePermissionService.selectSysRolePermissionList(sysRolePermission);
        return getDataTable(list);
    }


    /**
     * 根据主键查询
     *
     * @param sysRolePermission
     * @return 返回ResultInfo
     */
    @RequestMapping("/selectSysRolePermissionById")
    @ResponseBody
    public ResultInfo selectSysRolePermissionById(SysRolePermission sysRolePermission) {
    	SysRolePermission sysRolePermissionResult = sysRolePermissionService.selectSysRolePermissionById(sysRolePermission.getId());
        return ResultDataUtil.createSuccess(CommonEnum.SELECT_SUCCESS).setData(sysRolePermissionResult);
    }
    
    /**
     * 新增
     *
     * @param sysRolePermission 新增的记录
     * @return 返回ResultInfo
     */
    @RequestMapping("/save")
    @ResponseBody
    public ResultInfo save(SysRolePermission sysRolePermission) {
        Integer result = sysRolePermissionService.insertSysRolePermission(sysRolePermission);
        if (result< Constants.ONE) {
            ResultDataUtil.createFail(CommonEnum.SAVE_FAILURE);
        }
        return ResultDataUtil.createSuccess(CommonEnum.SAVE_SUCCESS);
    }
    
    
    /**
     * 修改，
     *
     * @param sysRolePermission 修改的记录
     * @return 返回ResultInfo
     */
    @RequestMapping("/update")
    @ResponseBody
    public ResultInfo update(SysRolePermission sysRolePermission) {
    	Integer result = sysRolePermissionService.updateSysRolePermission(sysRolePermission);
        if (result< Constants.ONE) {
            ResultDataUtil.createFail(CommonEnum.UPDATE_FAILURE);
        }
        return ResultDataUtil.createSuccess(CommonEnum.UPDATE_SUCCESS);
    }
    
    /**
     * 删除
     *
     * @param sysRolePermission 待删除的记录
     * @return 返回ResultInfo
     */
    @RequestMapping("/remove")
    @ResponseBody
    public ResultInfo remove(SysRolePermission sysRolePermission) {
    	Integer result = sysRolePermissionService.deleteSysRolePermissionById(sysRolePermission.getId());
        if (result< Constants.ONE) {
            ResultDataUtil.createFail(CommonEnum.DELETE_FAILURE);
        }
        return ResultDataUtil.createSuccess(CommonEnum.DELETE_SUCCESS);
    }

    /**
     * 查询角色和权限表的数据
     * @param sysRolePermission
     * @return
     */
    @RequestMapping(value = "/getRolePermission")
    @ResponseBody
    public ResultInfo getRolePermission(SysRolePermission sysRolePermission){
        List<SysPermission> sysPermission = sysRolePermissionService.getRolePermission(sysRolePermission);
        return ResultDataUtil.createSuccess(CommonEnum.LAYUI_SUCCESS).setData(sysPermission);
    }

    /**
     * 保存 角色 和权限表数据
     * @param sysRolePermission
     * @return
     */
    @RequestMapping(value = "/saveRelationship")
    @ResponseBody
    public ResultInfo saveRelationship(SysRolePermission sysRolePermission) {
        sysRolePermissionService.saveRelationship(sysRolePermission);
        return ResultDataUtil.createSuccess(CommonEnum.SAVE_SUCCESS);
    }


}