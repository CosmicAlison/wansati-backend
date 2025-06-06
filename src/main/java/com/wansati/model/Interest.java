package com.wansati.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import lombok.Builder.Default;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; 

    @ManyToMany(mappedBy = "interests")
    @Default
    private List<User> users = new ArrayList<>();
}