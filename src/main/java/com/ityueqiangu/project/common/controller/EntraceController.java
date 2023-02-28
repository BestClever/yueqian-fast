package com.ityueqiangu.project.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 用于主页
 * @ProjectName: yueqian-base
 * @PackageName: com.ityueqiangu.project.common.controller
 * @ClassName: EntraceController
 * @FileName: EntraceController.java
 * @CreateDate: 2022-03-14 20:16:55
 * @Author: FlowerStone
 * @Copyright
 */
@Controller
@RequestMapping
public class EntraceController {

    /**
     * 进入主页面
     *
     * @return
     * @author FlowerStone
     * @date 2021年11月11日 0011 15:39:42
     */
    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }

    /**
     * 进入欢迎页面
     *
     * @return
     * @author FlowerStone
     * @date 2021年11月11日 0011 15:40:39
     */
    @GetMapping(value = "/console")
    public String welcome() {
        return "console";
    }
}
