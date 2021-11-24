package com.ityueqiangu.project.portal.contoller;

import com.ityueqiangu.common.constant.Constants;
import com.ityueqiangu.core.web.controller.BaseController;
import com.ityueqiangu.core.web.domain.ResponseInfo;
import com.ityueqiangu.core.web.page.TableDataInfo;
import com.ityueqiangu.project.notice.domain.SysNotice;
import com.ityueqiangu.project.notice.service.ISysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description: 咨询实体类
 * @ProjectName: yueqian-fast
 * @PackageName: com.ityueqiangu.project.portal.contoller
 * @ClassName: InfomationController
 * @FileName: InfomationController.java
 * @CreateDate: 2021-11-23 16:27:45
 * @Author: FlowerStone
 * @Copyright
 */
@Controller
@RequestMapping(value = "/portal/information")
public class InformationController extends BaseController {

    @Autowired
    private ISysNoticeService sysNoticeService;

    /**
     * 列出所有的新闻
     * @author FlowerStone
     * @date 2021年11月23日 0023 17:47:18
     * @return
     */
    @GetMapping(value = "/listInformation")
    @ResponseBody
    public TableDataInfo listInformation(){
        startPage();
        SysNotice sysNoticeParam = new SysNotice();
        sysNoticeParam.setType(Constants.SUCCESS);
        List<SysNotice> sysNotices = sysNoticeService.selectSysNoticeList(sysNoticeParam);
        return getDataTable(sysNotices);
    }

    /**
     * 跳转到详情页面
     *
     * @param infoCode
     * @return
     * @author FlowerStone
     * @date 2021年11月23日 0023 16:32:27
     */
    @GetMapping(value = "/detail/{infoCode}")
    public String detail(@PathVariable(value = "infoCode") String infoCode, ModelMap mmp) {
        SysNotice sysNoticeParam = new SysNotice();
        sysNoticeParam.setInfoCode(infoCode);
        sysNoticeParam.setType(Constants.SUCCESS);
        SysNotice sysNotice = sysNoticeService.getOne(sysNoticeParam);
        mmp.addAttribute("sysNotice",sysNotice);
        return "portal/informationDetail";
    }
}
