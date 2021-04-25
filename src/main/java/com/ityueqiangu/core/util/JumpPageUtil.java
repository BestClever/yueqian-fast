package com.ityueqiangu.core.util;

import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author BestClever
 * @title: JumpPageUtil
 * @projectName springboot-study
 * @description: TODO
 * @date 2020-10-19 20:35
 */
public class JumpPageUtil {

    /**
     * 页面跳转
     * */
    public static ModelAndView JumpPage(String path){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(path);
        return modelAndView;
    }

    /**
     * 带参数的页面跳转
     * */
    public static ModelAndView JumpPage(String path, Map<String,?> params){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(path);
        modelAndView.addAllObjects(params);
        return modelAndView;
    }
}
