package com.ityueqiangu.core.config;

import com.ityueqiangu.core.constant.Constants;
import com.ityueqiangu.core.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * 通用配置
 *
 * @author FlowerStone
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer {
    /**
     * 首页地址
     */
    @Value("${user.indexUrl}")
    private String indexUrl;

    @Autowired
    private LoginInterceptor loginInterceptor;

    /*需要排除的请求*/
    private static List<String> EXCLUDE_PATH = Arrays.asList("/", "/login", "/register", "/error","/profile/**", "/captcha/generate", "/register.html", "/index2.html", "/component/**", "/favicon.ico", "/yueqian.config.yml");



    /**
     * 默认首页的设置，当输入域名是可以自动跳转到默认指定的网页
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:" + indexUrl);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /** 本地文件上传路径 */
        registry.addResourceHandler(Constants.RESOURCE_PREFIX + "/**").addResourceLocations("file:" + FrameworkConfig.getProfile() + "/");

        /** swagger配置 */
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 自定义拦截规则
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //门户端控制器
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(EXCLUDE_PATH);
    }
}