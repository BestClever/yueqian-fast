package com.ityueqiangu.system.controller;


import com.ityueqiangu.core.web.controller.BaseController;
import com.ityueqiangu.core.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ityueqiangu.core.web.result.DataGridResultInfo;
import com.ityueqiangu.core.web.result.ResultDataUtil;
import com.ityueqiangu.core.web.result.ResultInfo;
import org.springframework.stereotype.Controller;
import com.ityueqiangu.core.enums.CommonEnum;
import java.util.List;
import com.ityueqiangu.system.domain.SysNotice;
import com.ityueqiangu.system.service.ISysNoticeService;

/**
 * @author Clever、xia
 * @title: SysNoticeController
 * @description:
 * @date 2021-05-08 16:38:48
 */
@Controller
@RequestMapping(value = "/sysNotice")
public class SysNoticeController extends BaseController{

    @Autowired
    private ISysNoticeService sysNoticeService;
	/*访问路径前缀*/
    private final String PATH_PREFIX = "system/notice/";

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
     * 查看消息详情
     * @return
     */
    @RequestMapping(value = "/viewNotice")
    public String viewNotice(Integer id, ModelMap mmap){
        mmap.addAttribute("id",id);
        return PATH_PREFIX+"viewNotice";
    }
	
    /**
     * 分页列表查询
     * @param sysNotice
     * @return 返回DataGridResultInfo
     */
    @RequestMapping("/list")
    @ResponseBody
    public DataGridResultInfo list(SysNotice sysNotice) {
    	startPage();
        List<SysNotice> list = sysNoticeService.selectSysNoticeList(sysNotice);
        return getDataTable(list);
    }


    /**
     * 根据主键查询
     *
     * @param sysNotice
     * @return 返回ResultInfo
     */
    @RequestMapping("/selectSysNoticeById")
    @ResponseBody
    public ResultInfo selectSysNoticeById(SysNotice sysNotice) {
    	SysNotice sysNoticeResult = sysNoticeService.selectSysNoticeById(sysNotice.getId());
        return ResultDataUtil.createSuccess(CommonEnum.SELECT_SUCCESS).setData(sysNoticeResult);
    }
    
    /**
     * 新增
     *
     * @param sysNotice 新增的记录
     * @return 返回ResultInfo
     */
    @RequestMapping("/save")
    @ResponseBody
    public ResultInfo save(SysNotice sysNotice) {
        Integer result = sysNoticeService.insertSysNotice(sysNotice);
        if (result< Constants.ONE) {
            ResultDataUtil.createFail(CommonEnum.SAVE_FAILURE);
        }
        return ResultDataUtil.createSuccess(CommonEnum.SAVE_SUCCESS);
    }
    
    
    /**
     * 修改，
     *
     * @param sysNotice 修改的记录
     * @return 返回ResultInfo
     */
    @RequestMapping("/update")
    @ResponseBody
    public ResultInfo update(SysNotice sysNotice) {
    	Integer result = sysNoticeService.updateSysNotice(sysNotice);
        if (result< Constants.ONE) {
            ResultDataUtil.createFail(CommonEnum.UPDATE_FAILURE);
        }
        return ResultDataUtil.createSuccess(CommonEnum.UPDATE_SUCCESS);
    }
    
    /**
     * 删除
     *
     * @param sysNotice 待删除的记录
     * @return 返回ResultInfo
     */
    @RequestMapping("/remove")
    @ResponseBody
    public ResultInfo remove(SysNotice sysNotice) {
    	Integer result = sysNoticeService.deleteSysNoticeById(sysNotice.getId());
        if (result< Constants.ONE) {
            ResultDataUtil.createFail(CommonEnum.DELETE_FAILURE);
        }
        return ResultDataUtil.createSuccess(CommonEnum.DELETE_SUCCESS);
    }
}