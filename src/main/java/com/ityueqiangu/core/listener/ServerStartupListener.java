package com.ityueqiangu.core.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 功能描述: <br>
 *
 * @标题: ServerStartupListener
 * @作者 BestClever
 * @创建时间 2019-04-29 0029
 * @描述: 启动监听器
 */

public class ServerStartupListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        // 将web应用名称（路径）保存到application范围中
        ServletContext application = sce.getServletContext();
        String path = application.getContextPath();
        application.setAttribute("APP_PATH", path);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub

    }
}
