package com.wansati.service;

import com.wansati.model.Match;
import com.wansati.model.Swipe;
import com.wansati.model.User;
import com.wansati.repository.MatchRepository;
import com.wansati.repository.SwipeRepository;
import com.wansati.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SwipeRepository swipeRepository;
    private final MatchRepository matchRepository;

    public List<User> getCandidateMatches(Long userId) {
        User currentUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<User> swipedUsers = swipeRepository.findBySwiper(currentUser)
                .stream()
                .map(Swipe::getSwiped)
                .collect(Collectors.toList());

        List<Match> matches = matchRepository.findByUser1OrUser2(currentUser, currentUser);
        Set<User> matchedUsers = matches.stream()
                .flatMap(m -> Stream.of(m.getUser1(), m.getUser2()))
                .filter(u -> !u.equals(currentUser))
                .collect(Collectors.toSet());

        Set<Long> excludedIds = new HashSet<>();
        swipedUsers.forEach(u -> excludedIds.add(u.getId()));
        matchedUsers.forEach(u -> excludedIds.add(u.getId()));
        excludedIds.add(currentUser.getId()); 

        return userRepository.findByIdNotIn(new ArrayList<>(excludedIds));
    }
}
