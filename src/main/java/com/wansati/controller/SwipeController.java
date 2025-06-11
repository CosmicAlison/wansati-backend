package com.wansati.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wansati.dto.SwipeRequest;
import com.wansati.service.SwipeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/swipe")
public class SwipeController {

    private final SwipeService swipeService;

    @PostMapping
    public ResponseEntity<?> swipe(@RequestBody SwipeRequest request) {
        swipeService.swipe(request.getSwiperId(), request.getSwipedId(), request.isLiked());
        return ResponseEntity.ok("Swipe recorded");
    }
}
