package com.habit.service;

import java.util.List;

import com.habit.dto.CreateHabitDto;
import com.habit.dto.HabitDto;
import com.habit.dto.SummaryDto;

public interface HabitService {
	List<HabitDto> getAllForUser(Long userId);
	HabitDto create(Long userId,CreateHabitDto dto);
	HabitDto update(Long userId,Long habitId,CreateHabitDto dto);
	void delete(Long userId, Long habitId);
	HabitDto getById(Long userId,Long habitId);
	SummaryDto getSummary(Long userId);

}
