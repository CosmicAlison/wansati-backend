package com.wansati.model;

import lombok.*;
import java.time.Instant;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Chat {
    public enum ChatType {
    GROUP,
    PRIVATE
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Builder.Default
    private Instant sentAt = Instant.now();
    @Enumerated(EnumType.STRING)
    private ChatType type; 

    @ManyToMany
    @JoinTable(
        name = "chat_users",
        joinColumns = @JoinColumn(name = "chat_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    List<User> members;

    @Builder.Default
    private String name = "Chat";

    @OneToOne
    @JoinColumn(name = "last_message_id")
    private Message lastMessage;
  
}
