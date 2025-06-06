package com.wansati.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Employment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String title;
    private String startDate;
    private String endDate;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
