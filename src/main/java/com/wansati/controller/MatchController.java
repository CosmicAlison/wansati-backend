package com.wansati.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wansati.model.Match;
import com.wansati.model.User;
import com.wansati.repository.MatchRepository;
import com.wansati.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/match")
public class MatchController {
    private final MatchRepository matchRepository;
    private final UserRepository userRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Match>> getUserMatches(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Match> matches = matchRepository.findByUser1OrUser2(user, user);
        return ResponseEntity.ok(matches);
    }
}
