package com.chong.websocketchatting.controller;

import com.chong.websocketchatting.domain.ChatMessage;
import com.chong.websocketchatting.domain.MessageType;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Log
public class WebSocketEventListener {

    @Autowired
    private SimpMessageSendingOperations messageSendingOperations;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event){
        log.info("received new socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectionListener(SessionDisconnectEvent event){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if(username != null){
            log.info("user disconnected: " + username);

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setMessageType(MessageType.LEAVE);
            chatMessage.setSender(username);

            messageSendingOperations.convertAndSend("/topic/public", chatMessage);
        }

    }
}
