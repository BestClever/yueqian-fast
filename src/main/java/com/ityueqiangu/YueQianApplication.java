package com.ityueqiangu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

/**
 * @Description:
 * @ProjectName: yueqian-base
 * @PackageName: PACKAGE_NAME
 * @ClassName: YueqianApplication
 * @FileName: YueqianApplication.java
 * @CreateDate: 2022-02-24 11:12:48
 * @Author: FlowerStone
 * @Copyright
 */
@SpringBootApplication
public class YueQianApplication {
    public static void main(String[] args) {
        SpringApplication.run(YueQianApplication.class, args);
        //Properties properties = new Properties();
        //properties.setProperty("net.sf.ehcache.enableShutdownHook","true");
        //System.setProperties(properties);
    }
}
