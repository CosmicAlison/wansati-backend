package com.wansati.dto;

import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class SendMessageRequest {
    private Long senderId;
    private Long chatId;
    private String content;
}
