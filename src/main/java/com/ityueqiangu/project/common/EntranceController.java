package com.ityueqiangu.project.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xiacong
 * @date 2021-11-10 17:22:13
 */
@Controller
@RequestMapping
public class EntranceController {

    @RequestMapping
    public String toLogin(){
        return "login";
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }
}
