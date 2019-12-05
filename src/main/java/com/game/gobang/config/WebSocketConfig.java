package com.game.gobang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 项目名称：gobang-game-server
 * 类名称：WebSocketConfig
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/12/5
 * 修改人：yingx
 * 修改时间： 2019/12/5
 * 修改备注：
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
