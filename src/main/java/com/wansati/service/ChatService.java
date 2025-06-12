package com.wansati.service;

import com.wansati.model.Chat;
import com.wansati.model.User;
import com.wansati.repository.ChatRepository;
import com.wansati.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public Chat createPrivateChat(Long userId1, Long userId2) {
        User user1 = userRepository.findById(userId1)
                .orElseThrow(() -> new RuntimeException("User 1 not found"));
        User user2 = userRepository.findById(userId2)
                .orElseThrow(() -> new RuntimeException("User 2 not found"));

        List<Chat> existingChats = chatRepository.findByMembersContaining(user1);
        for (Chat chat : existingChats) {
            if (chat.getType() == Chat.ChatType.PRIVATE &&
                chat.getMembers().contains(user2) &&
                chat.getMembers().size() == 2) {
                return chat;
            }
        }

        Chat chat = Chat.builder()
                .type(Chat.ChatType.PRIVATE)
                .members(Arrays.asList(user1, user2))
                .build();

        return chatRepository.save(chat);
    }

    public Chat createGroupChat(String name, List<Long> userIds) {
        List<User> users = userRepository.findAllById(userIds);

        if (users.isEmpty()) {
            throw new RuntimeException("No valid users found for group chat");
        }

        Chat chat = Chat.builder()
                .name(name)
                .type(Chat.ChatType.GROUP)
                .members(users)
                .build();

        return chatRepository.save(chat);
    }
}
