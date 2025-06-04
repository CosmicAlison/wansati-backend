package com.wansati.model;

import java.time.Instant;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    private String password;

    private String name;
    @Builder.Default
    private String role = "USER";
    private String location;
    private String bio;
    @Builder.Default
    private boolean enabled = true;
    @Builder.Default
    private boolean locked = false;

    @Column(updatable = false)
    @Builder.Default
    private Instant createdAt = Instant.now();
}
