package com.habit.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habit.dto.CreateHabitDto;
import com.habit.dto.HabitDto;
import com.habit.dto.StatsDto;
import com.habit.dto.SummaryDto;
import com.habit.entities.Habit;
import com.habit.entities.HabitProgress;
import com.habit.entities.User;
import com.habit.repositories.HabitProgressRepository;
import com.habit.repositories.HabitRepository;
import com.habit.repositories.UserRepository;

@Service
public class HabitServiceImpl implements HabitService {

    @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HabitProgressRepository progressRepository;

    // ======================================================
    // GET ALL HABITS FOR USER
    // ======================================================
    @Override
    public List<HabitDto> getAllForUser(Long userId) {
        return habitRepository.findAllByUserId(userId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ======================================================
    // CREATE NEW HABIT
    // ======================================================
    @Override
    public HabitDto create(Long userId, CreateHabitDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Habit habit = new Habit();
        habit.setName(dto.getName());
        habit.setDescription(dto.getDescription());
        habit.setFrequency(Habit.Frequency.valueOf(dto.getFrequency().toUpperCase()));
        habit.setStartDate(LocalDate.parse(dto.getStartDate()));
        habit.setUser(user);

        Habit saved = habitRepository.save(habit);
        return toDto(saved);
    }

    // ======================================================
    // UPDATE HABIT
    // ======================================================
    @Override
    public HabitDto update(Long userId, Long habitId, CreateHabitDto dto) {
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new RuntimeException("Habit not found"));

        if (!habit.getUser().getId().equals(userId)) {
            throw new RuntimeException("Not authorized");
        }

        habit.setName(dto.getName());
        habit.setDescription(dto.getDescription());
        habit.setFrequency(Habit.Frequency.valueOf(dto.getFrequency().toUpperCase()));

        Habit updated = habitRepository.save(habit);
        return toDto(updated);
    }

    // ======================================================
    // DELETE HABIT
    // ======================================================
    @Override
    public void delete(Long userId, Long habitId) {
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new RuntimeException("Habit not found"));

        if (!habit.getUser().getId().equals(userId)) {
            throw new RuntimeException("Not authorized");
        }

        habitRepository.delete(habit);
    }

    // ======================================================
    // GET HABIT BY ID
    // ======================================================
    @Override
    public HabitDto getById(Long userId, Long habitId) {
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new RuntimeException("Habit not found"));

        if (!habit.getUser().getId().equals(userId)) {
            throw new RuntimeException("Not authorized");
        }

        return toDto(habit);
    }

    // ======================================================
    // PROFILE SUMMARY (TOTAL HABITS | STREAKS | AVG RATE)
    // ======================================================
    @Override
    public SummaryDto getSummary(Long userId) {

        List<Habit> habits = habitRepository.findAllByUserId(userId);

        SummaryDto summary = new SummaryDto();
        summary.setTotalHabits(habits.size());

        int completedToday = 0;
        double totalRates = 0;

        LocalDate today = LocalDate.now();

        for (Habit h : habits) {
            List<HabitProgress> progress = progressRepository.findByHabitIdOrderByDateAsc(h.getId());

            // count today completed
            for (HabitProgress p : progress) {
                if (p.getDate().equals(today) && p.isCompleted()) {
                    completedToday++;
                }
            }

            // compute rate
            int completed = (int) progress.stream().filter(HabitProgress::isCompleted).count();
            int total = progress.size();
            double rate = total == 0 ? 0 : (completed * 100.0 / total);
            totalRates += rate;
        }

        summary.setCompletedToday(completedToday);
        summary.setAvgCompletionRate(habits.isEmpty() ? 0 : totalRates / habits.size());

        return summary;
    }


    // ======================================================
    // CONVERT ENTITY → DTO
    // ======================================================
    private HabitDto toDto(Habit habit) {
        HabitDto dto = new HabitDto();
        dto.setId(habit.getId());
        dto.setName(habit.getName());
        dto.setDescription(habit.getDescription());
        dto.setFrequency(habit.getFrequency().name());
        dto.setStartDate(habit.getStartDate().toString());

        // Empty stats → actual values come from ProgressService
        StatsDto stats = new StatsDto();
        stats.setBestStreak(0);
        stats.setCurrentStreak(0);
        stats.setCompletionRate(0.0);

        dto.setStats(stats);

        return dto;
    }
}
