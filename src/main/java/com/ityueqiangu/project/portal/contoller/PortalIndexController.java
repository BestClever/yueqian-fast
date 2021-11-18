package com.ityueqiangu.project.portal.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 前端控制类
 * @ProjectName: yueqian-fast
 * @PackageName: com.ityueqiangu.project.portal.contoller
 * @ClassName: IndexController
 * @FileName: IndexController.java
 * @CreateDate: 2021-11-17 16:21:57
 * @Author: FlowerStone
 * @Copyright
 */
@Controller
@RequestMapping(value = "/portal")
public class PortalIndexController {

    @GetMapping("/login")
    public String login(){
        return "portal/login";
    }

    @GetMapping("/index")
    public String index(){
        return "portal/index";
    }
}
