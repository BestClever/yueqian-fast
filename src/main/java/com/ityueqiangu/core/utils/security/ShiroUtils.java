package com.ityueqiangu.core.utils.security;

import com.ityueqiangu.core.web.domain.ActiverUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 * shiro 工具类
 * 
 * @author Clever、xia
 */
public class ShiroUtils
{
    public static Subject getSubject()
    {
        return SecurityUtils.getSubject();
    }

    public static Session getSession()
    {
        return SecurityUtils.getSubject().getSession();
    }

    public static void logout()
    {
        getSubject().logout();
    }

    public static ActiverUser getSysUser()
    {
        return  (ActiverUser)getSubject().getPrincipal();
    }

    public static void setSysUser(ActiverUser activerUser)
    {
        Subject subject = getSubject();
        PrincipalCollection principalCollection = subject.getPrincipals();
        String realmName = principalCollection.getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(activerUser, realmName);
        // 重新加载Principal
        subject.runAs(newPrincipalCollection);
    }

    public static Long getUserId()
    {
        return getSysUser().getUser().getId().longValue();
    }

    public static String getLoginName()
    {
        return getSysUser().getUser().getLoginname();
    }

    public static String getIp()
    {
        return getSubject().getSession().getHost();
    }

    public static String getSessionId()
    {
        return String.valueOf(getSubject().getSession().getId());
    }
}
