package com.wansati.controller;

import com.wansati.model.User;
import com.wansati.repository.UserRepository;
import com.wansati.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtil jwtUtils;
    @PostMapping("/signin")
    public String authenticateUser(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtils.generateToken(userDetails.getUsername());
    }
    @PostMapping("/signup")
    public String registerUser(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return "Error: Username is already taken!";
        }

        User newUser = User.builder()
            .name(user.getName())
            .username(user.getUsername())
            .password(encoder.encode(user.getPassword()))
            .build();
        userRepository.save(newUser);
        return "User registered successfully!";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return "User not found.";
        }
        userRepository.deleteById(id);
        return "User deleted successfully.";
    }

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setBio(updatedUser.getBio());
            user.setLocation(updatedUser.getLocation());
            user.setProfileUrl(updatedUser.getProfileUrl());
            user.setInterests(updatedUser.getInterests());
            user.setCertifications(updatedUser.getCertifications());
            user.setSkills(updatedUser.getSkills());
            user.setEducationHistory(updatedUser.getEducationHistory());
            user.setEmploymentHistory(updatedUser.getEmploymentHistory()); 
            user.setUsername(updatedUser.getUsername()); 
            userRepository.save(user);
            return "User updated successfully!";
        }).orElse("User not found.");
    }



}