package com.ityueqiangu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author FlowerStone
 * @title: YueQianApplication
 * @projectName one_cartoon
 * @description: 启动程序
 * @date 2020-12-18 14:16
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class YueQianApplication {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(YueQianApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  跃迁系统启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}

