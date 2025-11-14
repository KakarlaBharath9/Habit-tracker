package com.habit.service;

import com.habit.dto.ProgressMarkDto;
import com.habit.dto.StatsDto;

public interface ProgressService {
	StatsDto markProgress(Long userId, ProgressMarkDto dto);
	StatsDto getStats(Long userId,Long habitId);
}
