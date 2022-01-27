package com.ityueqiangu.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @Description:
 * @ProjectName: ssm-schema-springboot
 * @PackageName: com.ityueqiangu.config
 * @ClassName: HikariDataSourceConfig
 * @FileName: HikariDataSourceConfig.java
 * @CreateDate: 2022-01-11 10:48:26
 * @Author: FlowerStone
 * @Copyright
 */
@Configuration
@Slf4j
public class HikariDataSourceConfig {

    //@Bean(name = "mainDataSource")
    //@ConfigurationProperties(prefix = "spring.datasource")
    //public DataSource masterDataSource(DataSourceProperties properties) {
    //    log.info("数据库连接池创建中.......",properties);
    //    return DataSourceBuilder.create().build();
    //}

    @Bean
    public ApplicationRunner runner(DataSource dataSource) {
        return args -> {
            log.info("dataSource: {}",dataSource);
            Connection connection = dataSource.getConnection();
            log.info("connection: {}",connection);
        };
    }
}
