package com.ityueqiangu.project.common;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.ityueqiangu.common.constant.Constants;
import com.ityueqiangu.common.exception.BusinessException;
import com.ityueqiangu.core.web.ActiverUser;
import com.ityueqiangu.core.web.domain.ResponseInfo;
import com.ityueqiangu.project.system.domain.SysUser;
import com.ityueqiangu.project.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author FlowerStone
 * @date 2021-11-10 17:22:13
 */
@Controller
@RequestMapping
public class IndexController {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 进入登录页面
     * @author FlowerStone
     * @date 2021年11月11日 0011 15:39:08
     * @return
     */
    @RequestMapping(value = "/login")
    public String toLogin(){
        return "login";
    }

    /**
     * 进入主页面
     * @author FlowerStone
     * @date 2021年11月11日 0011 15:39:42
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    /**
     * 进入欢迎页面
     * @author FlowerStone
     * @date 2021年11月11日 0011 15:40:39
     * @return
     */
    @RequestMapping(value = "/console")
    public String welcome(){
        return "console";
    }



    /**
     * 系统登录
     * @author FlowerStone
     * @date 2021年11月11日 0011 15:16:00
     * @param httpSession
     * @param activerUser
     * @return
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseInfo login(HttpSession httpSession, ActiverUser activerUser){
        //判断验证码是否正确
        String captchaCode = (String) httpSession.getAttribute(Constants.CAPTCHA_CODE);
        if (!StrUtil.equalsAnyIgnoreCase(captchaCode, activerUser.getCaptcha())) {
            throw new BusinessException("验证码错误！");
        }
        SysUser sysUser = new SysUser();
        sysUser.setUserName(activerUser.getUserName());
        SysUser resultSysUser = sysUserService.getOne(sysUser);
        if (ObjectUtil.isEmpty(resultSysUser)) {
            throw new BusinessException("用户不存在！");
        }
        if (!StrUtil.equals(SecureUtil.md5(activerUser.getPassword()),resultSysUser.getPassword())) {
            throw new BusinessException("密码错误！");
        }
        activerUser.setSysUser(resultSysUser);
        httpSession.setAttribute("activerUser",activerUser);
        return ResponseInfo.success("登录成功！").put("success",true);
    }
}
