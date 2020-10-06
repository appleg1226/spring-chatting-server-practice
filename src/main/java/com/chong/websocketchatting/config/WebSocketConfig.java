package com.chong.websocketchatting.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // STOMP 첫 연결을 위한 엔트리 포인트 설정
        registry.addEndpoint("/ws").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 요청을 하기 위해서 앞에 /app을 붙여서 해야한다.
        registry.setApplicationDestinationPrefixes("/app");

        // 어떤 채널로 보낼 것인가에 대한 주소 설정
        registry.enableSimpleBroker("/topic");
    }
}
