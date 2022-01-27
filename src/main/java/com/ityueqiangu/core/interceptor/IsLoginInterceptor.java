package com.ityueqiangu.core.interceptor;

import cn.hutool.core.util.ObjectUtil;
import com.ityueqiangu.common.utils.ServletUtils;
import com.ityueqiangu.common.utils.StringUtils;
import com.ityueqiangu.common.utils.UserUtil;
import com.ityueqiangu.core.aspectj.annotation.Logined;
import com.ityueqiangu.core.aspectj.annotation.RepeatSubmit;
import com.ityueqiangu.core.web.ActiverUser;
import com.ityueqiangu.core.web.domain.ResponseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * @Description:
 * @ProjectName: yueqian-fast
 * @PackageName: com.ityueqiangu.core.interceptor
 * @ClassName: IsLoginInterceptor
 * @FileName: IsLoginInterceptor.java
 * @CreateDate: 2022-01-07 10:14:00
 * @Author: FlowerStone
 * @Copyright
 */
@Slf4j
@Component
public class IsLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Logined annotation = method.getAnnotation(Logined.class);
            if (ObjectUtil.isNotEmpty(annotation)) {
                //1.判断是返回什么样的 结果 要么是 json  要么是  HTML
                RestController restController = method.getAnnotation(RestController.class);
                ResponseBody responseBody = method.getAnnotation(ResponseBody.class);
                ActiverUser activerUser = UserUtil.getCurrentUser();
                if (ObjectUtil.isNotEmpty(restController)||ObjectUtil.isNotEmpty(responseBody)) {
                    //进入这个条件的是 返回json
                    if (StringUtils.isNull(activerUser)) {
                        response.setHeader("SessionStatus", "sessionTimeOut");
                        response.setHeader("ServerType", "pc");
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//没有权限
                        ServletUtils.responseOutWithJson(response, ResponseInfo.error(ResponseInfo.Type.UNAUTHORIZED,"用户未授权，请重新登录",null));
                        return false;
                    }
                }else {
                    HttpSession session = request.getSession();
                    String path = session.getServletContext().getContextPath();
                    response.sendRedirect(path + "/portal/login");
                    return false;
                }
            }
            return true;
        }
        return true;
    }

}
