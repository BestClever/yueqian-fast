package com.ityueqiangu.project.portal.contoller;

import com.ityueqiangu.core.aspectj.annotation.Logined;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @ProjectName: yueqian-fast
 * @PackageName: com.ityueqiangu.project.portal.contoller
 * @ClassName: TestController
 * @FileName: TestController.java
 * @CreateDate: 2022-01-07 10:26:55
 * @Author: FlowerStone
 * @Copyright
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping(value = "/needLoginHtml")
    public String  needLoginHtml(){
        return "/portal/user/set";
    }

    @Logined
    @GetMapping(value = "/needLoginJson")
    @ResponseBody
    public String  needLoginJson(){
        return "/portal/user/set";
    }
}
