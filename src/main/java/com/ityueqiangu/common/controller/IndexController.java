package com.ityueqiangu.common.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import com.google.common.collect.Lists;
import com.ityueqiangu.core.enums.CommonEnum;
import com.ityueqiangu.core.exception.BizException;
import com.ityueqiangu.core.util.StringUtils;
import com.ityueqiangu.core.util.UserUtil;
import com.ityueqiangu.core.web.result.ResultDataUtil;
import com.ityueqiangu.core.web.result.ResultInfo;
import com.ityueqiangu.core.web.vo.ActiverUser;
import com.ityueqiangu.core.web.vo.Menu;
import com.ityueqiangu.system.enums.UserTypeEnums;
import com.ityueqiangu.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Clever、xia
 * @title: IndexController
 * @projectName teacher-evaluate-system
 * @description:
 * @date 2021-03-31 11:08
 */
@Controller
@RequestMapping
public class IndexController {

    @Autowired
    private IUserService userService;


    /**
     * 验证码生成类
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getVerifiyCode")
    public void validatePic(HttpServletRequest request, HttpServletResponse response) {
        RandomGenerator randomGenerator = new RandomGenerator("0123456789abcdefghklmnpqrstxyzABCDEFGHKLMNPQRSTXYZ", 4);
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(200, 100);
        captcha.setGenerator(randomGenerator);
        //得到code
        String code = captcha.getCode();
        System.out.println(code);
        //放到session
        request.getSession().setAttribute("sessionVerifyCode", code);
        //输出流
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            //读写输出流
            captcha.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭输出流
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 跳转到登录页面
     * @return
     */
    @GetMapping(value = "/login")
    public String toLogin(){
        return "login";
    }

    /**
     * 跳转到主页
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request){
        return "index";
    }

    /**
     * 跳转到个人主页
     * @return
     */
    @RequestMapping(value = "/personalInformation")
    public String personalInformation(HttpServletRequest request){
        return "personalInformation";
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request){
        //删除session
        request.getSession().removeAttribute("activerUser");
        return "redirect:login";
    }

    /**
     * 欢迎页
     * @return
     */
    @RequestMapping(value = "/main")
    public String main(){
        return "main";
    }

    /**
     * 获取菜单
     *
     */
    @RequestMapping(value = "/nav")
    @ResponseBody
    public List<Menu> getNav(HttpServletRequest request){
        ActiverUser currentUser = UserUtil.getCurrentUser();
        ArrayList<Menu> list = Lists.newArrayList();

        if (currentUser.getUserType().equals(UserTypeEnums.USER_TYPE_ADMIN.getKey())) {
            /**
             * 用户管理 模块
             */
            Menu yhgl = new Menu();
            List<Menu> yhglChildren = new ArrayList<>();
            Menu adminMenu = new Menu();
            adminMenu.setId(11);
            adminMenu.setTitle("用户管理");
            adminMenu.setHref("sysUser/index");
            adminMenu.setIcon("&#xe612;");
            adminMenu.setSpread(false);
            adminMenu.setParentId(1);
            yhglChildren.add(adminMenu);

            yhgl.setId(1);
            yhgl.setTitle("用户管理");
            yhgl.setIcon("&#xe653;");
            yhgl.setHref("sysUser/index");
            yhgl.setSpread(false);
            yhgl.setChildren(yhglChildren);
            list.add(yhgl);

            /*学院管理*/
            Menu xygl = new Menu();
            List<Menu> xyglChildren = new ArrayList<>();
            Menu collegeMenu = new Menu();
            collegeMenu.setId(21);
            collegeMenu.setTitle("学院管理");
            collegeMenu.setIcon("&#xe65b;");
            collegeMenu.setHref("college/index");
            collegeMenu.setSpread(false);
            xyglChildren.add(collegeMenu);
            Menu classeMenu = new Menu();
            classeMenu.setId(21);
            classeMenu.setTitle("班级管理");
            classeMenu.setIcon("&#xe65a;");
            classeMenu.setHref("classe/index");
            classeMenu.setSpread(false);
            xyglChildren.add(classeMenu);
            xygl.setId(2);
            xygl.setTitle("学院管理");
            xygl.setIcon("&#xe656;");
            xygl.setHref("");
            xygl.setSpread(false);
            xygl.setChildren(xyglChildren);
            list.add(xygl);
        }
        /*考核管理*/
        Menu khgl = new Menu();
        List<Menu> khglChildren = new ArrayList<>();
        if (currentUser.getUserType().equals(UserTypeEnums.USER_TYPE_ADMIN.getKey())) {
            Menu khtk = new Menu();
            khtk.setId(31);
            khtk.setTitle("考核题库");
            khtk.setIcon("&#xe615;");
            khtk.setHref("assessLibrary/index");
            khtk.setSpread(false);
            khglChildren.add(khtk);

            Menu khpg = new Menu();
            khpg.setId(32);
            khpg.setTitle("考核评估");
            khpg.setIcon("&#xe620;");
            khpg.setHref("assess/index");
            khpg.setSpread(false);
            khglChildren.add(khpg);
        }
        if (!currentUser.getUserType().equals(UserTypeEnums.USER_TYPE_ADMIN.getKey())) {
            Menu fdypj = new Menu();
            fdypj.setId(33);
            fdypj.setTitle("用户评价");
            fdypj.setIcon("&#xe620;");
            fdypj.setHref("assess/userAssess");
            fdypj.setSpread(false);
            khglChildren.add(fdypj);
        }
        if (currentUser.getUserType().equals(UserTypeEnums.USER_TYPE_ADMIN.getKey())) {
            Menu khpc = new Menu();
            khpc.setId(34);
            khpc.setTitle("考核批次");
            khpc.setIcon("&#xe674;");
            khpc.setHref("assessBatch/index");
            khpc.setSpread(false);
            khglChildren.add(khpc);
        }
        if (currentUser.getUserType().equals(UserTypeEnums.USER_TYPE_ASSISTANT.getKey())) {
            Menu wpjtx = new Menu();
            wpjtx.setId(35);
            wpjtx.setTitle("未评价提醒");
            wpjtx.setIcon("&#xe675;");
            wpjtx.setHref("assess/notEvaluate");
            wpjtx.setSpread(false);
            khglChildren.add(wpjtx);
        }
        khgl.setId(3);
        khgl.setTitle("考核管理");
        khgl.setIcon("&#xe62d;");
        khgl.setHref("");
        khgl.setSpread(false);
        khgl.setChildren(khglChildren);
        list.add(khgl);

        /*评价记录*/
        Menu pjjl = new Menu();
        List<Menu> pjjlChildren = new ArrayList<>();
        Menu wdpjjj = new Menu();
        wdpjjj.setId(41);
        wdpjjj.setTitle("我的评价记录");
        wdpjjj.setIcon("&#xe62f;");
        wdpjjj.setHref("assess/myAssess");
        wdpjjj.setSpread(false);
        pjjlChildren.add(wdpjjj);

        if (currentUser.getUserType().equals(UserTypeEnums.USER_TYPE_TEACHER.getKey())) {
            Menu jspjjj = new Menu();
            jspjjj.setId(42);
            jspjjj.setTitle("老师评价记录");
            jspjjj.setIcon("&#xe613;");
            jspjjj.setHref("assess/teacherAssess");
            jspjjj.setSpread(false);
            pjjlChildren.add(jspjjj);

            Menu jspgjj = new Menu();
            jspgjj.setId(43);
            jspgjj.setTitle("老师评估记录");
            jspgjj.setIcon("&#xe614;");
            jspgjj.setHref("assessEvaluate/teacherAssessEvaluate");
            jspgjj.setSpread(false);
            pjjlChildren.add(jspgjj);
        }

        pjjl.setId(4);
        pjjl.setTitle("评价记录");
        pjjl.setIcon("&#xe612;");
        pjjl.setHref("");
        pjjl.setSpread(false);
        pjjl.setChildren(pjjlChildren);
        list.add(pjjl);


        return list;
    }

    /**
     * 登录方法
     * @param session
     * @param activerUser
     * @return
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public ResultInfo login(HttpSession session, ActiverUser activerUser){
        String sessionVerifyCode = (String) session.getAttribute("sessionVerifyCode");
        if (!StringUtils.equalsIgnoreCase(activerUser.getVerifyCode(),sessionVerifyCode)) {
            throw new BizException(CommonEnum.RANDOMIMAGE_FAILURE);
        }
        ActiverUser user = userService.login(activerUser);
        session.setAttribute("activerUser",user);
        return ResultDataUtil.createSuccess(CommonEnum.LOGIN_SUCCESS);
    }
}
