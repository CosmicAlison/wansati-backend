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
public class Message {

    public enum ChatType {
        GROUP,
        PRIVATE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Builder.Default
    private Instant sentAt = Instant.now();

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver; 
    private String content;

    @Enumerated(EnumType.STRING)
    private ChatType type; 
}
