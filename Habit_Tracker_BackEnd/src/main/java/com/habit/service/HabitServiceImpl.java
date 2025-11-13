package com.habit.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habit.dto.CreateHabitDto;
import com.habit.dto.HabitDto;
import com.habit.dto.StatsDto;
import com.habit.entities.Habit;
import com.habit.entities.User;
import com.habit.repositories.HabitRepository;
import com.habit.repositories.UserRepository;

@Service
public class HabitServiceImpl implements HabitService {
	@Autowired
	private HabitRepository habitRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<HabitDto>getAllForUser(Long userId){
		return habitRepository.findAllByUserId(userId)
				.stream()
				.map(this::toDto)
				.collect(Collectors.toList());
	}
	@Override
	public HabitDto create(Long userId, CreateHabitDto dto) {
		User user = userRepository.findById(userId)
				.orElseThrow(()->new RuntimeException("User not found"));
		Habit habit = new Habit();
		habit.setName(dto.getName());
		habit.setDescription(dto.getDescription());
		habit.setFrequency(Habit.Frequency.valueOf(dto.getFrequency()));
		habit.setStartDate(LocalDate.parse(dto.getStartDate()));
		habit.setUser(user);
		
		Habit saved = habitRepository.save(habit);
		return toDto(saved);
	}
	@Override
	public HabitDto update(Long userId, Long habitId, CreateHabitDto dto) {
		Habit habit = habitRepository.findById(habitId)
				.orElseThrow(()->new RuntimeException("Habit not found"));
		if(!habit.getUser().getId().equals(userId)) {
			throw new RuntimeException("Not authorized");
		}
		habit.setName(dto.getName());
		habit.setDescription(dto.getDescription());
		habit.setFrequency(Habit.Frequency.valueOf(dto.getFrequency()));
		
		Habit updated = habitRepository.save(habit);
		return toDto(updated);
	}
	@Override
	public void delete(Long userId, Long habitId) {
		Habit habit = habitRepository.findById(habitId)
				.orElseThrow(()->new RuntimeException("Habit not found"));
		if (!habit.getUser().getId().equals(userId)) {
			throw new RuntimeException("Not authorized");
		}
		habitRepository.delete(habit);
	}
	@Override
	public HabitDto getById(Long userid,Long habitId) {
		Habit habit = habitRepository.findById(habitId)
				.orElseThrow(()->new RuntimeException("Habit not found"));
		if(!habit.getUser().getId().equals(userid)) {
			throw new RuntimeException("Not authorized");
		}
		return toDto(habit);
	}
	private HabitDto toDto(Habit habit) {
		HabitDto dto = new HabitDto();
		dto.setId(habit.getId());
		dto.setName(habit.getName());
		dto.setDescription(habit.getDescription());
		dto.setFrequency(habit.getFrequency().name());
		dto.setStartDate(habit.getStartDate().toString());
		
		
		//stats empty for now (ProgressService will fill this later)
		StatsDto stats =new StatsDto();
		stats.setBestStreak(0);
		stats.setCurrentStreak(0);
		stats.setCompletionRate(0.0);
		
		dto.setStats(stats);
		return dto;
	}
}
