package com.wansati.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String degree;
    private String institution;
    private String field;
    private String startYear;
    private String endYear;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

