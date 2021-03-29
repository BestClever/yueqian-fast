package com.ityueqiangu.core.config;


import com.ityueqiangu.core.constant.Constants;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Clever、xia
 * @title: ResourcesConfig
 * @projectName tutor-service-platform
 * @description:
 * @date 2021-03-09 08:53
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        /** 本地文件上传路径 */
        registry.addResourceHandler(Constants.RESOURCE_PREFIX + "/**").addResourceLocations("file:" + FrameworkConfig.getProfile() + "/");

    }
}
