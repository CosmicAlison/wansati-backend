package com.wansati.dto;

import java.time.Instant;

import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ChatMessageDTO {
    private Long chatId;
    private Long senderId;
    private String senderUsername;
    private String content;
    private Instant sentAt;    
}
