package com.wansati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import com.wansati.model.User;
import com.wansati.model.Chat;
import com.wansati.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChat(Chat chat);
}