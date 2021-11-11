package com.ityueqiangu.project.common;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.CodeGenerator;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.util.StrUtil;
import com.ityueqiangu.common.constant.Constants;
import com.ityueqiangu.core.web.controller.BaseController;
import com.ityueqiangu.core.web.domain.ResponseInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.channels.SeekableByteChannel;

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
    @RequestMapping("/generate")
    public void generate(HttpServletRequest request, HttpServletResponse response){
        RandomGenerator randomGenerator = new RandomGenerator("0123456789abcdefghklmnpqrstxyzABCDEFGHKLMNPQRSTXYZ", 4);
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(130, 48);
        captcha.setGenerator(randomGenerator);
        //得到code
        String code = captcha.getCode();
        //放到session
        request.getSession().setAttribute(Constants.CAPTCHA_CODE, code);
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
     * 异步验证
     * @param session 请求报文
     * @param captcha 验证码
     * @return 验证结果
     * */
    @RequestMapping("verify")
    public ResponseInfo verify(HttpSession session, String captcha){
//        String sessionVerifyCode = (String) session.getAttribute("sessionVerifyCode");;
//        if (StrUtil.equalsAnyIgnoreCase(captcha, sessionVerifyCode)) {
//
//        }
        return ResponseInfo.error();
    }
}
