package com.chong.websocketchatting.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    private MessageType messageType;
    private String content;
    private String sender;
}
