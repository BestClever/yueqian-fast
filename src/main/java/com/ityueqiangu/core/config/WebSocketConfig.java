package com.ityueqiangu.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Description:
 * @ProjectName: yueqian-base
 * @PackageName: com.ityueqiangu.core.config
 * @ClassName: WebSocketConfig
 * @FileName: WebSocketConfig.java
 * @CreateDate: 2022-03-15 17:03:41
 * @Author: FlowerStone
 * @Copyright
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
