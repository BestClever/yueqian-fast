package com.ityueqiangu.project.common.controller;

import cn.hutool.core.util.StrUtil;
import com.ityueqiangu.core.annotation.NoLogined;
import com.ityueqiangu.core.web.controller.BaseController;
import com.ityueqiangu.core.web.domain.ResponseInfo;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author FlowerStone
 * @date 2021-11-11 13:46:16
 */
@Controller
@RequestMapping("/captcha")
public class CaptchaController extends BaseController {

    /**
     * 验证码生成
     * @param request 请求报文
     * @param response 响应报文
     * */
    @GetMapping("/generate")
    @NoLogined
    public void generate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CaptchaUtil.out(request, response);
    }

    /**
     * 异步验证
     * @param session 请求报文
     * @param captcha 验证码
     * @return 验证结果
     * */
    @GetMapping("verify")
    public ResponseInfo verify(HttpSession session, String captcha){
        String sessionVerifyCode = (String) session.getAttribute("sessionVerifyCode");;
        if (StrUtil.equalsAnyIgnoreCase(captcha, sessionVerifyCode)) {

        }
        return ResponseInfo.error();
    }
}
