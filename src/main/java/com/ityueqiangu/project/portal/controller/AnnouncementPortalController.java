package com.ityueqiangu.project.portal.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ityueqiangu.core.annotation.NoLogined;
import com.ityueqiangu.core.constant.Constants;
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
@RequestMapping(value="/portal/announcement")
public class AnnouncementPortalController extends BaseController {

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

    /**
     * 分页查询
     *
     * @return 分页数据
     */
    @GetMapping("/list")
    @ResponseBody
    @NoLogined
    public TableDataInfo list(Announcement announcement) {
        startPage();
        announcement.setType(Constants.YES);
        List<Announcement> list = announcementService.selectAnnouncementList(announcement);
        return getDataTable(list);
    }
}