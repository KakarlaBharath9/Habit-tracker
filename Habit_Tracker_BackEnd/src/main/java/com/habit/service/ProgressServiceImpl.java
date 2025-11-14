package com.habit.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habit.dto.ProgressMarkDto;
import com.habit.dto.StatsDto;
import com.habit.entities.Habit;
import com.habit.entities.HabitProgress;
import com.habit.repositories.HabitProgressRepository;
import com.habit.repositories.HabitRepository;

@Service
public class ProgressServiceImpl implements ProgressService{
	@Autowired
	private HabitProgressRepository progressRepository;
	@Autowired
	private HabitRepository habitRepository;
	
	@Override
	public StatsDto markProgress(Long userId, ProgressMarkDto dto) {
		Habit habit = habitRepository.findById(dto.getHabitId())
				.orElseThrow(()->new RuntimeException("Habit not found"));
		if(!habit.getUser().getId().equals(userId))
			throw new RuntimeException("Not Authorized");
		LocalDate date=LocalDate.parse(dto.getDate());
		HabitProgress progress = progressRepository
				.findByHabitIdAndDate(dto.getHabitId(), date)
				.orElse(new HabitProgress());
		progress.setHabit(habit);
		progress.setDate(date);
		progress.setCompleted(dto.isCompleted());
		
		progressRepository.save(progress);
		 return calculateStats(habit.getId());
		
	}
	
	@Override
	public StatsDto getStats(Long userId,Long habitId) {
		Habit habit = habitRepository.findById(habitId)
				.orElseThrow(()->new RuntimeException("Habit not found"));
		if(!habit.getUser().getId().equals(userId))
			throw new RuntimeException("Not authorized");
		return calculateStats(habitId);
	}
	private StatsDto calculateStats(Long habitId) {
		List<HabitProgress>progressList=progressRepository.findByHabitIdOrderByDateAsc(habitId);
		int currentStreak=0;
		int bestStreak=0;
		int completedDays=0;
		
		int streak=0;
		
		for(HabitProgress p:progressList) {
			if(p.isCompleted()) {
				completedDays++;
				streak++;
				bestStreak=Math.max(bestStreak,streak);
			}else {
				streak=0;
			}
		}
		currentStreak=streak;
		double completionRate=
				progressList.isEmpty()?0.0:
					(completedDays*100.0)/progressList.size();
		
		StatsDto stats=new StatsDto();
		stats.setBestStreak(bestStreak);
		stats.setCurrentStreak(currentStreak);
		stats.setCompletionRate(completionRate);
		
		return stats;
	}

}
