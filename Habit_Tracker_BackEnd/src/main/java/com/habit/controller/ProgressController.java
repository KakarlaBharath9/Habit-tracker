package com.habit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.habit.dto.ProgressMarkDto;
import com.habit.dto.StatsDto;
import com.habit.entities.User;
import com.habit.repositories.UserRepository;
import com.habit.service.ProgressService;
@RestController
@RequestMapping("/api/progress")
public class ProgressController {
	@Autowired
	private ProgressService progressService;
	@Autowired
	private UserRepository userRepository;
	
	//extract userId from Jwt Authentication
	private Long getUserId(Authentication authentication) {
		String username=authentication.getName();
		User user = userRepository.findByUsername(username)
				.orElseThrow(()->new RuntimeException("User not found"));
		return user.getId();
	}
	//mark progress (complete/not complete for a day)
	@PostMapping("/mark")
	public ResponseEntity<StatsDto>markProgress(
			@RequestBody ProgressMarkDto dto,
			Authentication authentication
			){
		Long userId = getUserId(authentication);
		StatsDto stats = progressService.markProgress(userId, dto);
		return ResponseEntity.ok(stats);
	}
	@GetMapping("/{habitId}/stats")
	public ResponseEntity<StatsDto>getStats(
			@PathVariable Long habitId,
			Authentication authentication
			){
		Long userId = getUserId(authentication);
		StatsDto stats = progressService.getStats(userId, habitId);
		return ResponseEntity.ok(stats);
	}
}
