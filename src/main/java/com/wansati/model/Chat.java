package com.wansati.model;

import lombok.*;
import java.time.Instant;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Builder
@Getter
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
    List<User> members;
    @Builder.Default
    private String name = "Chat";
    
}
