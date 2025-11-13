package com.habit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.habit.configuration.JwtUtils;
import com.habit.dto.JwtResponse;
import com.habit.dto.LoginDto;
import com.habit.dto.RegisterDto;
import com.habit.entities.User;
import com.habit.repositories.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired UserRepository userRepository;
	@Autowired AuthenticationManager authenticationManager;
	@Autowired PasswordEncoder encoder;
	@Autowired JwtUtils jwtUtils;
	
	@PostMapping("/register")
	public ResponseEntity<?>register(@RequestBody RegisterDto dto){
		if(userRepository.existsByUsername(dto.getUsername())) return ResponseEntity.badRequest().body("Username taken");
		User u = new User();
		u.setUsername(dto.getUsername());
		u.setEmail(dto.getEmail());
		u.setPassword(encoder.encode(dto.getPassword()));
		userRepository.save(u);
		return ResponseEntity.ok("User registered");
	}
	
	@PostMapping("/login")
	public ResponseEntity<?>login(@RequestBody LoginDto dto){
		Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwt=jwtUtils.generateJwtToken(auth);
		return ResponseEntity.ok(new JwtResponse(jwt));
	}
}
