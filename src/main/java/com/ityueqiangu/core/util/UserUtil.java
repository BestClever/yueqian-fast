package com.ityueqiangu.core.util;


import com.ityueqiangu.core.web.vo.ActiverUser;

import javax.servlet.http.HttpServletRequest;

/**
 * @author BestClever
 * @title: UserUtil
 * @description: TODO
 * @date 2020-11-04 10:56
 */
public class UserUtil {

    /**
     * 获取当前登录的用户信息
     * @return
     */
    public static ActiverUser getCurrentUser(){
        HttpServletRequest request = ServletUtils.getRequest();
        ActiverUser loginUser = (ActiverUser) request.getSession().getAttribute("activerUser");
        return loginUser;
    }
}
