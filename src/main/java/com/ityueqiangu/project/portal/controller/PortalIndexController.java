package com.ityueqiangu.project.portal.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.system.UserInfo;
import com.ityueqiangu.core.annotation.NoLogined;
import com.ityueqiangu.core.constant.Constants;
import com.ityueqiangu.core.exception.BusinessException;
import com.ityueqiangu.core.utils.ServletUtils;
import com.ityueqiangu.core.utils.UserUtil;
import com.ityueqiangu.core.web.controller.BaseController;
import com.ityueqiangu.core.web.domain.ActiverUserInfo;
import com.ityueqiangu.core.web.domain.ResponseInfo;
import com.ityueqiangu.project.carousel.domain.Carousel;
import com.ityueqiangu.project.carousel.service.ICarouselService;
import com.ityueqiangu.project.userinfo.domain.User;
import com.ityueqiangu.project.userinfo.service.IUserService;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description:
 * @ProjectName: yueqian-base
 * @PackageName: com.ityueqiangu.project.portal.controller
 * @ClassName: PortalIndexController
 * @FileName: PortalIndexController.java
 * @CreateDate: 2022-04-24 09:24:29
 * @Author: FlowerStone
 * @Copyright
 */
@Controller
@RequestMapping(value = "/portal")
public class PortalIndexController extends BaseController {

    @Autowired
    private ICarouselService carouselService;

    @Autowired
    private IUserService userService;

    /**
     * 跳转到主页面
     *
     * @return
     * @date 2022年04月24日 0024 9:25:30
     */
    @GetMapping(value = "/index")
    @NoLogined
    public String index(ModelMap mmp) {
        Carousel carousel = new Carousel();
        carousel.setType(Constants.YES);
        carousel.setIsDisplay(Constants.YES);
        List<Carousel> carousels = carouselService.selectCarouselList(carousel);
        mmp.addAttribute("carouselList", carousels);
        return "portal/index";
    }

    /**
     * 跳转到登录页面
     *
     * @return
     * @date 2022年04月24日 0024 19:35:40
     */
    @GetMapping(value = "/login")
    @NoLogined
    public String toLogin() {
        return "portal/login";
    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     * @date 2022年04月09日 0009 15:37:57
     */
    @GetMapping(value = "/logout")
    @NoLogined
    public String logout(HttpSession session) {
        session.removeAttribute(Constants.FRONT_USER_INFO);
        return "redirect:index";
    }

    /**
     * 跳转到注册页面
     *
     * @return
     * @date 2022年04月08日 0008 21:33:27
     */
    @GetMapping(value = "/register")
    @NoLogined
    public String toRegister() {
        return "portal/register";
    }

    /**
     * 前端用户登录
     *
     * @param activerUserInfo
     * @return
     * @date 2022年04月08日 0008 22:19:39
     */
    @PostMapping(value = "/login")
    @ResponseBody
    @NoLogined
    public ResponseInfo login(HttpServletRequest request, ActiverUserInfo activerUserInfo) {
        //判断验证码是否正确
        if (!CaptchaUtil.ver(activerUserInfo.getCaptcha(), request)) {
            throw new BusinessException("验证码错误！");
        }
        //查询用户表 根据用户名获取相应的结果
        User user = new User();
        user.setLoginName(activerUserInfo.getLoginName());
        User userInfoResult = userService.getOne(user);
        if (ObjectUtil.isEmpty(userInfoResult)) {
            throw new BusinessException("用户不存在！");
        }
        if (!StrUtil.equals(activerUserInfo.getPassword(), userInfoResult.getPassword())) {
            throw new BusinessException("密码错误！");
        }
        if (!StrUtil.equals(userInfoResult.getIsActive(), Constants.YES)) {
            throw new BusinessException("账号未启用，请联系管理员");
        }
        //设置session
        UserUtil.refreshPortalUser(userInfoResult);
        return ResponseInfo.success();
    }

    /**
     * 注册
     *
     * @return
     * @date 2022年04月09日 0009 6:48:44
     */
    @PostMapping(value = "/register")
    @ResponseBody
    @NoLogined
    public ResponseInfo register(@Validated ActiverUserInfo activerUserInfo) {
        User params = new User();
        params.setLoginName(activerUserInfo.getLoginName());
        User one = userService.getOne(params);
        if (ObjectUtil.isNotEmpty(one)) {
            throw new BusinessException("已经存在该登录名称了，请换一个再进行注册！");
        }
        if (ObjectUtil.isNotEmpty(one) && StrUtil.equals(activerUserInfo.getPhone(), one.getPhone())) {
            throw new BusinessException("手机号已经注册过了！");
        }
        //保存用户信息
        User user = new User();
        user.setLoginName(activerUserInfo.getLoginName());
        user.setUserName(activerUserInfo.getUserName());
        user.setPassword(activerUserInfo.getPassword());
        user.setSex(activerUserInfo.getSex());
        user.setPhone(activerUserInfo.getPhone());
        user.setIsActive(Constants.YES);

        userService.insertUser(user);
        return ResponseInfo.success("注册成功");
    }
}
