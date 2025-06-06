package com.wansati.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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
    @Builder.Default
    private String profileUrl = "";

    @Column(updatable = false)
    @Builder.Default
    private Instant createdAt = Instant.now();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Certification> certifications = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Education> educationHistory = new ArrayList<>();

    @ManyToMany
    @Builder.Default
    private List<Interest> interests = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Employment> employmentHistory = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "user_skill",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    @Builder.Default
    private List<Skill> skills = new ArrayList<>();
}
