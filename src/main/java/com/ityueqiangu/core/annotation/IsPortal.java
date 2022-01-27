package com.ityueqiangu.core.annotation;

import java.lang.annotation.*;

/**
 * @Description: 请求是前端的注解
 * @ProjectName: yueqian-fast
 * @PackageName: com.ityueqiangu.core.aspectj.annotation
 * @ClassName: Logined
 * @FileName: Logined.java
 * @CreateDate: 2022-01-07 10:13:11
 * @Author: FlowerStone
 * @Copyright
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IsPortal {

    /**
     * 是否需要已经登录才允许访问
     *
     * @return
     */
    boolean isLogined() default true;
}
