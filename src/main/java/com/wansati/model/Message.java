package com.wansati.model;

import java.time.Instant;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Builder.Default
    private Instant sentAt = Instant.now();

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    private String content;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = true)
    private Chat chat;
}
