package com.wansati.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wansati.dto.SendMessageRequest;
import com.wansati.model.Chat;
import com.wansati.model.Message;
import com.wansati.repository.ChatRepository;
import com.wansati.repository.MessageRepository;
import com.wansati.service.MessageService;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/message")
public class MessageController {
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    @GetMapping("/{chatId}")
    public ResponseEntity<List<Message>> getChatMessages(@PathVariable Long chatId) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new RuntimeException("Chat not found"));

        List<Message> messages = messageRepository.findByChat(chat);
        return ResponseEntity.ok(messages);
    }

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload SendMessageRequest request) {
        Message saved = messageService.sendMessage(
            request.getChatId(),
            request.getSenderId(),
            request.getContent()
        );
        messagingTemplate.convertAndSend("/topic/" + request.getChatId(), saved);
    }
}

