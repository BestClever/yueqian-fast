package com.ityueqiangu.core.shiro;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Clever、xia
 * @title: MyAuthenticationFilter
 * @projectName yueqian-fast
 * @description:
 * @date 2021-03-23 14:23
 */
public class MyAuthenticationFilter extends FormAuthenticationFilter {

    private static final String USR_LOGIN_URL = "/usr/page/login";
    private static final String ADMIN_LOGIN_URL = "/sys/toLogin";

    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI();
        //如果包含
        if(url.contains("/portal/")){
            WebUtils.issueRedirect(request, response, USR_LOGIN_URL);
        }else {
            WebUtils.issueRedirect(request, response, ADMIN_LOGIN_URL);
        }

    }
}
