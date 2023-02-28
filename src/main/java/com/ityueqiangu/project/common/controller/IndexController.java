package com.ityueqiangu.project.common.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.ityueqiangu.core.annotation.NoLogined;
import com.ityueqiangu.core.constant.Constants;
import com.ityueqiangu.core.exception.BusinessException;
import com.ityueqiangu.core.utils.UserUtil;
import com.ityueqiangu.core.web.domain.ActiverUser;
import com.ityueqiangu.core.web.domain.ResponseInfo;
import com.ityueqiangu.core.web.domain.SysMenu;
import com.ityueqiangu.project.system.domain.SysUser;
import com.ityueqiangu.project.system.service.ISysUserService;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author FlowerStone
 * @date 2021-11-10 17:22:13
 */
@Controller
@RequestMapping(value = "/admin")
public class IndexController {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 进入登录页面
     *
     * @return
     * @author FlowerStone
     * @date 2021年11月11日 0011 15:39:08
     */
    @GetMapping(value = "/login")
    @NoLogined
    public String toLogin() {
        return "login";
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

    /**
     * 跳转到错误页面
     * @author FlowerStone
     * @date 2021年11月17日 0017 21:21:06
     * @return
     */
    @GetMapping(value = "/error")
    @NoLogined
    public String error(){
        return "error/404";
    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     * @author FlowerStone
     * @date 2021年11月11日 0011 17:58:23
     */
    @PostMapping(value = "/logout")
    @ResponseBody
    public ResponseInfo logout(HttpSession session) {
        //删除session
        session.removeAttribute(Constants.SYS_USER_INFO);
        return ResponseInfo.success("注销成功！");
    }


    /**
     * 系统登录
     *
     * @param request
     * @param activerUser
     * @return
     * @author FlowerStone
     * @date 2021年11月11日 0011 15:16:00
     */
    @PostMapping(value = "/login")
    @ResponseBody
    @NoLogined
    public ResponseInfo login(HttpServletRequest request, ActiverUser activerUser) {
        //判断验证码是否正确
        if (!CaptchaUtil.ver(activerUser.getCaptcha(), request)) {
            throw new BusinessException("验证码错误！");
        }
        SysUser sysUser = new SysUser();
        sysUser.setUserName(activerUser.getUserName());
        SysUser resultSysUser = sysUserService.getOne(sysUser);
        if (ObjectUtil.isEmpty(resultSysUser)) {
            throw new BusinessException("用户不存在！");
        }
        if (!StrUtil.equals(SecureUtil.md5(activerUser.getPassword()), resultSysUser.getPassword())) {
            throw new BusinessException("密码错误！");
        }
        if(!StrUtil.equals(resultSysUser.getIsEnable(), Constants.YES)){
            throw new BusinessException("账号未启用，请联系管理员");
        }
        UserUtil.refreshUser(resultSysUser);
        return ResponseInfo.success("登录成功！");
    }

    @GetMapping(value = "/getUserMenu")
    @ResponseBody
    public List<SysMenu> getUserMenu(){
        ActiverUser currentUser = UserUtil.getCurrentUser();
        return sysUserService.getUserMenu(currentUser.getSysUser().getUserName());
    }
}
