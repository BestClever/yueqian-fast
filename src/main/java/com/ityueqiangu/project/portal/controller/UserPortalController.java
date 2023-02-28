package com.ityueqiangu.project.portal.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.system.UserInfo;
import com.ityueqiangu.core.annotation.IsPortal;
import com.ityueqiangu.core.utils.UserUtil;
import com.ityueqiangu.core.web.controller.BaseController;
import com.ityueqiangu.project.system.domain.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @ProjectName: yueqian-base
 * @PackageName: com.ityueqiangu.project.portal.controller
 * @ClassName: UserPortalController
 * @FileName: UserPortalController.java
 * @CreateDate: 2022-04-24 20:00:03
 * @Author: FlowerStone
 * @Copyright
 */
@Controller
@RequestMapping(value = "/portal/user")
public class UserPortalController extends BaseController {

    /**
     * 跳转到用户中心
     *
     * @return
     * @date 2022年04月10日 0010 11:40:11
     */
    @GetMapping(value = "/userCenter")
    @IsPortal
    public String userCenter() {
        return "portal/user/userCenter";
    }
}
