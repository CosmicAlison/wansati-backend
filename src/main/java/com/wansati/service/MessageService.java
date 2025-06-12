package com.wansati.service;

import com.wansati.model.Chat;
import com.wansati.model.Message;
import com.wansati.model.User;
import com.wansati.repository.ChatRepository;
import com.wansati.repository.MessageRepository;
import com.wansati.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public Message sendMessage(Long chatId, Long senderId, String content) {
        Chat chat = chatRepository.findById(chatId)
            .orElseThrow(() -> new RuntimeException("Chat not found"));

        User sender = userRepository.findById(senderId)
            .orElseThrow(() -> new RuntimeException("Sender not found"));

        Message message = Message.builder()
            .chat(chat)
            .sender(sender)
            .content(content)
            .build();

        Message saved = messageRepository.save(message);

        chat.setLastMessage(saved);
        chatRepository.save(chat);

        return saved;
    }
}
