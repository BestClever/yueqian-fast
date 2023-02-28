package com.ityueqiangu.project.announcement.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ityueqiangu.core.utils.PrimaryKeyUtils;
import com.ityueqiangu.core.utils.UserUtil;
import com.ityueqiangu.core.web.controller.BaseController;
import com.ityueqiangu.core.web.domain.ActiverUser;
import com.ityueqiangu.core.web.domain.ResponseInfo;
import com.ityueqiangu.core.web.page.TableDataInfo;
import com.ityueqiangu.project.announcement.domain.Announcement;
import com.ityueqiangu.project.announcement.service.IAnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author FlowerStone
 * @title: AnnouncementController
 * @description:
 * @date 2022-04-04 22:32:40
 */
@Controller
@RequestMapping(value="/admin/announcement")
public class AnnouncementController extends BaseController {

    @Autowired
    private IAnnouncementService announcementService;
    
    @GetMapping(value = "/index")
    public String index(){
        return "announcement/index";
    }

    @GetMapping(value = "/add")
    public String add(){
        return "announcement/add";
    }

    @GetMapping(value = "/edit")
    public String edit(Integer id, ModelMap mmap){
        Announcement announcement = announcementService.selectAnnouncementById(id);
        mmap.addAttribute("announcement",announcement);
        return "announcement/edit";
    }

    /**
     * 分页查询
     *
     * @return 分页数据
     */
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Announcement announcement) {
        startPage();
        List<Announcement> list = announcementService.selectAnnouncementList(announcement);
        return getDataTable(list);
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
        return ResponseInfo.success(announcementService.selectAnnouncementById(id));
    }    
     
    /**
     * 新增
     *
     * @param announcement 新增的记录
     * @return 返回影响行数
     */
    @PostMapping("/insert")
    @ResponseBody
    public ResponseInfo insert(@RequestBody Announcement announcement) {
        ActiverUser currentUser = UserUtil.getCurrentUser();
        announcement.setInfoCode(PrimaryKeyUtils.generateOrderNo());
        announcement.setPublishBy(ObjectUtil.isNotEmpty(currentUser)?currentUser.getLoginName():"");
        announcement.setPublishTime(DateUtil.date());
        return toAjax(announcementService.insertAnnouncement(announcement));
    }    
      
    /**
     * 修改
     *
     * @param announcement 修改的记录
     * @return 返回影响行数
     */
    @PostMapping("/update")
    @ResponseBody
    public ResponseInfo update(@RequestBody Announcement announcement) {
        return toAjax(announcementService.updateAnnouncement(announcement));
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
        return toAjax(announcementService.deleteAnnouncementById(id));
    }
    
}