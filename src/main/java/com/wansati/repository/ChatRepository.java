package com.wansati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import com.wansati.model.User;
import com.wansati.model.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByMembersContaining(User user);
}
