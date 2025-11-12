package com.habit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.habit.entities.Habit;

public interface HabitRepository extends JpaRepository<Habit,Long> {
	List<Habit> findAllByUserId(Long userId);
	

}
