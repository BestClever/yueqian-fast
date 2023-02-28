package com.ityueqiangu.core.utils;


import cn.hutool.system.UserInfo;
import com.ityueqiangu.core.constant.Constants;
import com.ityueqiangu.core.web.domain.ActiverUser;
import com.ityueqiangu.core.web.domain.ActiverUserInfo;
import com.ityueqiangu.project.system.domain.SysUser;
import com.ityueqiangu.project.userinfo.domain.User;

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
        ActiverUser loginUser = (ActiverUser) request.getSession().getAttribute(Constants.SYS_USER_INFO);
        return loginUser;
    }


    /**
     * 获取当前登录的用户信息 用户端
     *
     * @return
     */
    public static ActiverUserInfo getCurrentUserInfo() {
        HttpServletRequest request = ServletUtils.getRequest();
        ActiverUserInfo activerUserInfo = (ActiverUserInfo) request.getSession().getAttribute(Constants.FRONT_USER_INFO);
        return activerUserInfo;
    }

    /**
     * 刷新登录信息
     *
     * @param sysUser
     * @return
     * @date 2022年03月14日 0014 19:33:12
     */
    public static void refreshUser(SysUser sysUser) {
        ActiverUser activerUser = new ActiverUser();
        activerUser.setUserId(sysUser.getId());
        activerUser.setUserName(sysUser.getRealName());
        activerUser.setAvatar(sysUser.getAvatar());
        activerUser.setLoginName(sysUser.getUserName());
        activerUser.setDeptId(sysUser.getDeptId());
        activerUser.setEmail(sysUser.getEmail());
        activerUser.setIsDeleted(sysUser.getIsDeleted());
        activerUser.setRoleName(sysUser.getRealName());
        activerUser.setSysUser(sysUser);
        ServletUtils.getRequest().getSession().setAttribute(Constants.SYS_USER_INFO, activerUser);
    }

    /**
     * 刷新前端用户
     *
     * @return
     * @date 2022年04月24日 0024 9:32:12
     */
    public static void refreshPortalUser(User user) {
        ActiverUserInfo userInfo = new ActiverUserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setAvatar(user.getAvatar());
        userInfo.setLoginName(user.getLoginName());
        userInfo.setUserName(user.getUserName());
        userInfo.setIsDeleted(user.getIsDeleted());
        userInfo.setUser(user);
        ServletUtils.getRequest().getSession().setAttribute(Constants.FRONT_USER_INFO, userInfo);
    }

}
