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

    /**
     * 登录
     * @author FlowerStone
     * @date 2021年11月18日 0018 10:48:25
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "portal/login";
    }

    /**
     *  注册
     * @author FlowerStone
     * @date 2021年11月18日 0018 10:48:17
     * @return
     */
    @GetMapping("/register")
    public String register(){
        return "portal/register";
    }

    @GetMapping("/index")
    public String index(){
        return "portal/index";
    }
}
