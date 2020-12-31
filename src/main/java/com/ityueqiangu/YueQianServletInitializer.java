package com.ityueqiangu;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author BestClever
 * @title: YueQianServletInitializer
 * @projectName one_cartoon
 * @description: web容器中进行部署
 * @date 2020-12-18 14:15
 */
public class YueQianServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(YueQianApplication.class);
    }
}
