package com.habit.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.habit.entities.HabitProgress;

public interface HabitProgressRepository extends JpaRepository<HabitProgress,Long> {
	Optional<HabitProgress>findByHabitIdandDate(Long habitId,LocalDate date);
	List<HabitProgress>findByHabitIdOrderByDateAsc(Long habitId);
	List<HabitProgress>findByHabitIdAndDateBetween(Long habitId,LocalDate start,LocalDate end);
	
}
