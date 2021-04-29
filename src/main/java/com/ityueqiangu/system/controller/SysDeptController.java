package com.ityueqiangu.system.controller;


import cn.hutool.core.util.ObjectUtil;
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

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.ityueqiangu.system.domain.SysDept;
import com.ityueqiangu.system.service.ISysDeptService;

/**
 * @author Clever、xia
 * @title: SysDeptController
 * @description:
 * @date 2021-04-25 17:33:01
 */
@Controller
@RequestMapping(value = "/sysDept")
public class SysDeptController extends BaseController{

    @Autowired
    private ISysDeptService sysDeptService;
	/*访问路径前缀*/
    private final String PATH_PREFIX = "system/";

	 /**
     * 访问主页
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(){
        return PATH_PREFIX+"dept/index";
    }

    /**
     * 新增
     * @return
     */
    @RequestMapping(value = "/add")
    public String add(){
        return PATH_PREFIX+"dept/add";
    }

    /**
     * 修改
     * @return
     */
    @RequestMapping(value = "/edit")
    public String edit(){
        return PATH_PREFIX+"dept/edit";
    }
	
    /**
     * 分页列表查询
     * @param sysDept
     * @return 返回DataGridResultInfo
     */
    @RequestMapping("/list")
    @ResponseBody
    public DataGridResultInfo list(SysDept sysDept) {
    	startPage();
        List<SysDept> list = sysDeptService.selectSysDeptList(sysDept);
        //按照排序码 进行排序
        list = list.stream().sorted(Comparator.comparing(SysDept::getSortNum)).collect(Collectors.toList());
        return getDataTable(list);
    }

    /**
     * 生成部门树
     * @return
     */
    @RequestMapping(value = "tree")
    @ResponseBody
    public ResultInfo tree(){
        return ResultDataUtil.createSuccess(CommonEnum.LAYUI_SUCCESS).setData(sysDeptService.buildTree());
    }


    /**
     * 根据主键查询
     *
     * @param sysDept
     * @return 返回ResultInfo
     */
    @RequestMapping("/selectSysDeptById")
    @ResponseBody
    public ResultInfo selectSysDeptById(SysDept sysDept) {
    	SysDept sysDeptResult = sysDeptService.selectSysDeptById(sysDept.getId());
        return ResultDataUtil.createSuccess(CommonEnum.SELECT_SUCCESS).setData(sysDeptResult);
    }
    
    /**
     * 新增
     *
     * @param sysDept 新增的记录
     * @return 返回ResultInfo
     */
    @RequestMapping("/save")
    @ResponseBody
    public ResultInfo save(SysDept sysDept) {
        Integer result = sysDeptService.insertSysDept(sysDept);
        if (result< Constants.ONE) {
            ResultDataUtil.createFail(CommonEnum.SAVE_FAILURE);
        }
        return ResultDataUtil.createSuccess(CommonEnum.SAVE_SUCCESS);
    }
    
    
    /**
     * 修改，
     *
     * @param sysDept 修改的记录
     * @return 返回ResultInfo
     */
    @RequestMapping("/update")
    @ResponseBody
    public ResultInfo update(SysDept sysDept) {
    	Integer result = sysDeptService.updateSysDept(sysDept);
        if (result< Constants.ONE) {
            ResultDataUtil.createFail(CommonEnum.UPDATE_FAILURE);
        }
        return ResultDataUtil.createSuccess(CommonEnum.UPDATE_SUCCESS);
    }
    
    /**
     * 删除
     *
     * @param sysDept 待删除的记录
     * @return 返回ResultInfo
     */
    @RequestMapping("/remove")
    @ResponseBody
    public ResultInfo remove(SysDept sysDept) {
    	Integer result = sysDeptService.deleteSysDeptById(sysDept.getId());
        if (result< Constants.ONE) {
            ResultDataUtil.createFail(CommonEnum.DELETE_FAILURE);
        }
        return ResultDataUtil.createSuccess(CommonEnum.DELETE_SUCCESS);
    }

    /**
     * 查询部门名称是否存在
     * @param sysDept
     * @return
     */
    @RequestMapping(value = "/existDeptName")
    @ResponseBody
    public ResultInfo existDeptName(SysDept sysDept){
        SysDept result = sysDeptService.existDeptName(sysDept);
        if (ObjectUtil.isNotNull(result)) {
            return  ResultDataUtil.createFail(CommonEnum.DATA_EXIST);
        }
        return ResultDataUtil.createSuccess(CommonEnum.DATA_NOT_EXIST);
    }
}