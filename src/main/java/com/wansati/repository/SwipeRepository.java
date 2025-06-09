package com.wansati.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wansati.model.Swipe;
import com.wansati.model.User;

@Repository
public interface SwipeRepository extends JpaRepository<Swipe, Long> {
    Optional<Swipe> findBySwiperAndSwiped(User swiper, User swiped);
}

