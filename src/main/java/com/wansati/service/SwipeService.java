package com.wansati.service;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wansati.model.Swipe;
import com.wansati.model.Match;
import com.wansati.model.User;
import com.wansati.repository.UserRepository;
import com.wansati.repository.SwipeRepository;
import com.wansati.repository.MatchRepository;

@Service
@RequiredArgsConstructor
public class SwipeService {
    private final SwipeRepository swipeRepo;
    private final MatchRepository matchRepo;
    private final UserRepository userRepo;

    public void swipe(Long swiperId, Long swipedId, boolean liked) {
        User swiper = userRepo.findById(swiperId).orElseThrow(() -> new RuntimeException("Swiper user not found"));
        User swiped = userRepo.findById(swipedId).orElseThrow(() -> new RuntimeException("Swiped user not found"));

        Swipe swipe = Swipe.builder()
                .swiper(swiper)
                .swiped(swiped)
                .liked(liked)
                .timestamp(Instant.now())
                .build();

        swipeRepo.save(swipe);

        if (liked) {
            Optional<Swipe> reverseSwipe = swipeRepo.findBySwiperAndSwiped(swiped, swiper);
            if (reverseSwipe.isPresent() && reverseSwipe.get().isLiked()) {
                Match match = Match.builder()
                        .user1(swiper)
                        .user2(swiped)
                        .matchedAt(Instant.now())
                        .build();
                matchRepo.save(match);
            }
        }
    }    
}
