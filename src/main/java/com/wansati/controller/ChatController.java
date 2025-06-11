package com.wansati.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wansati.model.Chat;
import com.wansati.model.Message;
import com.wansati.model.User;
import com.wansati.repository.ChatRepository;
import com.wansati.repository.MessageRepository;
import com.wansati.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/message")
public class ChatController {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Message>> getUserMessages(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Chat> chats = chatRepository.findByMembersContaining(user);
        List<Message> messages = null;
        for (Chat chat : chats) {
            messages = messageRepository.findByChat(chat);
        }
        return ResponseEntity.ok(messages);
    }
}

