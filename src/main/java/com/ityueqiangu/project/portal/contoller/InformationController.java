package com.ityueqiangu.project.portal.contoller;

import com.ityueqiangu.project.notice.domain.SysNotice;
import com.ityueqiangu.project.notice.service.ISysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class InformationController {

    @Autowired
    private ISysNoticeService sysNoticeService;

    /**
     * 跳转到详情页面
     *
     * @param infoCode
     * @return
     * @author FlowerStone
     * @date 2021年11月23日 0023 16:32:27
     */
    @RequestMapping(value = "/detail/{infoCode}")
    public String detail(@PathVariable(value = "infoCode") String infoCode, ModelMap mmp) {
        SysNotice sysNoticeParam = new SysNotice();
        sysNoticeParam.setInfoCode(infoCode);
        SysNotice sysNotice = sysNoticeService.getOne(sysNoticeParam);
        mmp.addAttribute("sysNotice",sysNotice);
        return "portal/informationDetail";
    }
}
