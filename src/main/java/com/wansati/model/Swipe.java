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
public class Swipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "swiper_id")
    private User swiper;

    @ManyToOne
    @JoinColumn(name = "swiped_id")
    private User swiped;

    private boolean liked;
    private Instant timestamp;
}
