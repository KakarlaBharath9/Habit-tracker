package com.habit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.habit.configuration.JwtUtils;
import com.habit.dto.JwtResponse;
import com.habit.dto.LoginDto;
import com.habit.dto.RegisterDto;
import com.habit.entities.User;
import com.habit.repositories.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private UserRepository userRepository;
    @Autowired private AuthenticationManager authManager;
    @Autowired private PasswordEncoder encoder;
    @Autowired private JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto dto) {

        if (userRepository.existsByUsername(dto.getUsername()))
            return ResponseEntity.badRequest().body("Username taken");

        User u = new User();
        u.setUsername(dto.getUsername());
        u.setEmail(dto.getEmail());
        u.setPassword(encoder.encode(dto.getPassword()));
        userRepository.save(u);
        
        //auto login after registration
        Authentication auth = authManager.authenticate(
        		new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword()));
        String jwt = jwtUtils.generateJwtToken(auth);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {

        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );

        String jwt = jwtUtils.generateJwtToken(auth);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }
    
    @GetMapping("/me")
    public User getCurrentUser(Authentication auth) {
        return userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
