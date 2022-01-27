package com.ityueqiangu.core.utils;


import cn.hutool.system.UserInfo;
import com.ityueqiangu.core.web.domain.ActiverUser;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author FlowerStone
 * @date 2021-11-10 21:37:49
 */
public class UserUtil {

    public static Map<Integer, UserInfo> userInfoMap = new HashMap<>();

    /**
     * 获取当前登录的用户信息 管理端
     *
     * @return
     */
    public static ActiverUser getCurrentUser() {
        HttpServletRequest request = ServletUtils.getRequest();
        ActiverUser loginUser = (ActiverUser) request.getSession().getAttribute("activerUser");
        return loginUser;
    }
}
