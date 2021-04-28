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
import com.ityueqiangu.system.domain.SysPermission;
import com.ityueqiangu.system.service.ISysPermissionService;

/**
 * @author Clever、xia
 * @title: SysPermissionController
 * @description:
 * @date 2021-04-27 16:58:27
 */
@Controller
@RequestMapping(value = "/sysPermission")
public class SysPermissionController extends BaseController{

    @Autowired
    private ISysPermissionService sysPermissionService;
	/*访问路径前缀*/
    private final String PATH_PREFIX = "system/permission/";

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
     * @param sysPermission
     * @return 返回DataGridResultInfo
     */
    @RequestMapping("/list")
    @ResponseBody
    public DataGridResultInfo list(SysPermission sysPermission) {
    	startPage();
        List<SysPermission> list = sysPermissionService.selectSysPermissionList(sysPermission);
        return getDataTable(list);
    }


    /**
     * 根据主键查询
     *
     * @param sysPermission
     * @return 返回ResultInfo
     */
    @RequestMapping("/selectSysPermissionById")
    @ResponseBody
    public ResultInfo selectSysPermissionById(SysPermission sysPermission) {
    	SysPermission sysPermissionResult = sysPermissionService.selectSysPermissionById(sysPermission.getId());
        return ResultDataUtil.createSuccess(CommonEnum.SELECT_SUCCESS).setData(sysPermissionResult);
    }
    
    /**
     * 新增
     *
     * @param sysPermission 新增的记录
     * @return 返回ResultInfo
     */
    @RequestMapping("/save")
    @ResponseBody
    public ResultInfo save(SysPermission sysPermission) {
        Integer result = sysPermissionService.insertSysPermission(sysPermission);
        if (result< Constants.ONE) {
            ResultDataUtil.createFail(CommonEnum.SAVE_FAILURE);
        }
        return ResultDataUtil.createSuccess(CommonEnum.SAVE_SUCCESS);
    }
    
    
    /**
     * 修改，
     *
     * @param sysPermission 修改的记录
     * @return 返回ResultInfo
     */
    @RequestMapping("/update")
    @ResponseBody
    public ResultInfo update(SysPermission sysPermission) {
    	Integer result = sysPermissionService.updateSysPermission(sysPermission);
        if (result< Constants.ONE) {
            ResultDataUtil.createFail(CommonEnum.UPDATE_FAILURE);
        }
        return ResultDataUtil.createSuccess(CommonEnum.UPDATE_SUCCESS);
    }
    
    /**
     * 删除
     *
     * @param sysPermission 待删除的记录
     * @return 返回ResultInfo
     */
    @RequestMapping("/remove")
    @ResponseBody
    public ResultInfo remove(SysPermission sysPermission) {
    	Integer result = sysPermissionService.deleteSysPermissionById(sysPermission.getId());
        if (result< Constants.ONE) {
            ResultDataUtil.createFail(CommonEnum.DELETE_FAILURE);
        }
        return ResultDataUtil.createSuccess(CommonEnum.DELETE_SUCCESS);
    } 
}