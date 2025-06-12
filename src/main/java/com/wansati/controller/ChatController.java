package com.wansati.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wansati.model.Chat;
import com.wansati.model.User;
import com.wansati.repository.ChatRepository;
import com.wansati.repository.UserRepository;
import com.wansati.service.ChatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final ChatService chatService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Chat>> getUserMessages(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Chat> chats = chatRepository.findByMembersContaining(user);
        return ResponseEntity.ok(chats);
    }

    @PostMapping("/private")
    public ResponseEntity<Chat> createPrivateChat(@RequestParam Long userId1, @RequestParam Long userId2) {
        Chat chat = chatService.createPrivateChat(userId1, userId2);
        return ResponseEntity.ok(chat);
    }

    @PostMapping("/group")
    public ResponseEntity<Chat> createGroupChat(@RequestParam String name, @RequestBody List<Long> userIds) {
        Chat chat = chatService.createGroupChat(name, userIds);
        return ResponseEntity.ok(chat);
    }

}

