package com.wansati.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wansati.dto.SwipeRequest;
import com.wansati.model.User;
import com.wansati.service.SwipeService;
import com.wansati.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/swipe")
public class SwipeController {

    private final SwipeService swipeService;
    private final UserService userService; 

    @PostMapping
    public ResponseEntity<?> swipe(@RequestBody SwipeRequest request) {
        swipeService.swipe(request.getSwiperId(), request.getSwipedId(), request.isLiked());
        return ResponseEntity.ok("Swipe recorded");
    }

    @GetMapping("/candidateMatches/{userId}")
    public ResponseEntity<List<User>> getCandidateMatches(@PathVariable Long userId) {
        List<User> candidates = userService.getCandidateMatches(userId);
        return ResponseEntity.ok(candidates);
    }

}
