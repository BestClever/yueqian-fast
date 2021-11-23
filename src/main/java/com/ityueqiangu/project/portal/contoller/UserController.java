package com.ityueqiangu.project.portal.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 用户模块
 * @ProjectName: yueqian-fast
 * @PackageName: com.ityueqiangu.project.portal.contoller
 * @ClassName: UserController
 * @FileName: UserController.java
 * @CreateDate: 2021-11-23 11:11:23
 * @Author: FlowerStone
 * @Copyright
 */
@RequestMapping(value = "/user")
@Controller
public class UserController {

    /**
     * 个人中心
     *
     * @return
     * @author FlowerStone
     * @date 2021年11月23日 0023 9:56:12
     */
    @GetMapping(value = "/user")
    public String userIndex() {
        return "portal/user/index";
    }

    /**
     * 用户设置
     * @author FlowerStone
     * @date 2021年11月23日 0023 11:10:51
     * @return
     */
    @GetMapping(value = "/userSet")
    public String userSet(){
        return "portal/user/set";
    }
}
