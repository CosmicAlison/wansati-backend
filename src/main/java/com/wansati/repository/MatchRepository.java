package com.wansati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wansati.model.Match;
import com.wansati.model.User;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByUser1OrUser2(User user1, User user2);
}
