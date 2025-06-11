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

    private String sender;
    private String receiver; 
    private String content;

    @Enumerated(EnumType.STRING)
    private ChatType type; 


}
