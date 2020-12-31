package com.ityueqiangu.project.system.user.controller;

import com.ityueqiangu.common.exception.BusinessException;
import com.ityueqiangu.framework.shiro.service.RegisterService;
import com.ityueqiangu.framework.web.controller.BaseController;
import com.ityueqiangu.framework.web.domain.AjaxResult;
import com.ityueqiangu.project.system.config.service.IConfigService;
import com.ityueqiangu.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 注册验证
 * 
 * @author Clever、xia
 */
@Controller
public class RegisterController extends BaseController
{
    @Autowired
    private RegisterService registerService;

    @Autowired
    private IConfigService configService;

    @GetMapping("/register")
    public String register()
    {
        return "register";
    }

    @GetMapping("/forgetPwd")
    public String forgetPwd()
    {
        return "forgetPwd";
    }

    @PostMapping("/register")
    @ResponseBody
    public AjaxResult ajaxRegister(User user)
    {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser"))))
        {
            throw new BusinessException("当前系统没有开启注册功能！");
        }
        String loginName = registerService.register(user);
        return AjaxResult.success("注册成功").put("loginName",loginName);
    }

    @PostMapping("/forgetPwd")
    @ResponseBody
    public AjaxResult forgetPwd(User user)
    {
        registerService.forgetPwd(user);
        return AjaxResult.success("重置密码成功");
    }
}
