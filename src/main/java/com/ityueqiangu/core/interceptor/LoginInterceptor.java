package com.ityueqiangu.core.interceptor;

import cn.hutool.core.util.ObjectUtil;
import com.ityueqiangu.core.annotation.IsPortal;
import com.ityueqiangu.core.annotation.NoLogined;
import com.ityueqiangu.core.utils.ServletUtils;
import com.ityueqiangu.core.utils.UserUtil;
import com.ityueqiangu.core.web.domain.ActiverUser;
import com.ityueqiangu.core.web.domain.ResponseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * @author FlowerStone
 * @date 2021-11-11 09:43:43
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 在控制器执行之前完成业务逻辑操作
     * 方法的返回值决定逻辑是否继续执行， true，表示继续执行， false, 表示不再继续执行。
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long start = System.currentTimeMillis();
        request.setAttribute("START_TIME", start);
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            NoLogined annotation = method.getAnnotation(NoLogined.class);
            // 如果包含不需要登录的注解 直接放行
            if (ObjectUtil.isNotEmpty(annotation)) {
                return true;
            }

            ActiverUser activerUser = UserUtil.getCurrentUser();
            // 判断当前用户是否已经登陆
            HttpSession session = request.getSession();
            request.setCharacterEncoding("utf-8"); // 设置编码为utf初-8
            response.setContentType("text/html;charset=utf-8");

            if (ObjectUtil.isNull(activerUser)) {
                // Ajax请求会话过期处理
                if (ServletUtils.isAjaxRequest(request)) {
                    response.setHeader("SessionStatus", "sessionTimeOut");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//没有权限
                    ServletUtils.responseOutWithJson(response, ResponseInfo.unauthorized("用户未授权，请重新登录！", null));
                    return false;
                }
                String path = session.getServletContext().getContextPath();
                //如果是 authenticate 开头的请求表明是 前端用户模块 这时候需要跳转到 前端的登录界面
                IsPortal isPortalAnnotation = method.getAnnotation(IsPortal.class);
                if (ObjectUtil.isNotEmpty(isPortalAnnotation)) {
                    response.sendRedirect(path + "/portal/login");
                }else {
                    response.sendRedirect(path + "/login");
                }

                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    /**
     * 在完成视图渲染之后，执行此方法。
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        String url = request.getRequestURI().toString();
        long start = (long) request.getAttribute("START_TIME");
        long end = System.currentTimeMillis();
        log.info("======request finished. url:{},cost:{}", url, end - start);
    }
}
