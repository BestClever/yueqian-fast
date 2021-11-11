package com.ityueqiangu.common.utils;

import com.ityueqiangu.core.web.ActiverUser;

import javax.servlet.http.HttpServletRequest;

/**
 * @author FlowerStone
 * @date 2021-11-10 21:37:49
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
