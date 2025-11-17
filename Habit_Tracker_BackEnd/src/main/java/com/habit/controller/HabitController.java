package com.habit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.habit.dto.CreateHabitDto;
import com.habit.dto.HabitDto;
import com.habit.entities.User;
import com.habit.repositories.UserRepository;
import com.habit.service.HabitService;
//import com.sun.security.auth.UserPrincipal;

@RestController
@RequestMapping("/api/habits")
public class HabitController {
	@Autowired 
	private HabitService habitService;
	@Autowired
	private UserRepository userRepository;
	
	//getting logged-in user id from jwt token
	private Long getUserId(Authentication authentication) {
		String username=authentication.getName();
		User user=userRepository.findByUsername(username)
				.orElseThrow(()->new RuntimeException("User not found"));
		return user.getId();
	}
	// list all habits for logged-in user
	@GetMapping("/all")
	public List<HabitDto>getAllForUser(Authentication authentication){
		Long userId = getUserId(authentication);
		return habitService.getAllForUser(userId);
	}
	//Get habit by Id
	@GetMapping("/{habitId}")
	public HabitDto getHabitById(@PathVariable Long habitId, Authentication authentication) {
		Long userId = getUserId(authentication);
		return habitService.getById(userId, habitId);
	}
	//create Habit
	@PostMapping
	public HabitDto createHabit(@RequestBody CreateHabitDto dto,Authentication authentication) {
		Long userId=getUserId(authentication);
		return habitService.create(userId,dto);
	}
	//update habit
	@PutMapping("/{habitId}")
	public HabitDto updateHabit(
			@PathVariable Long habitId,
			@RequestBody CreateHabitDto dto,
			Authentication authentication
			) {
				Long userId=getUserId(authentication);
				return habitService.update(userId, habitId, dto);
	}
	//delete habit
	@DeleteMapping("/{habitId}")
	public void deleteHabit(@PathVariable Long habitId,Authentication authentication) {
		Long userId=getUserId(authentication);
		habitService.delete(userId, habitId);
	}
	
}
