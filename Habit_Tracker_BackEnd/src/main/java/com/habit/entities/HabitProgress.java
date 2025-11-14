package com.habit.entities;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name="habit_proogress",uniqueConstraints=@UniqueConstraint(columnNames= {"habit_id","date"}))

public class HabitProgress {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="habit_id")
	private Habit habit;
	private LocalDate date;
	private boolean completed;
	
	public HabitProgress() {}
	//getters and setters
	public Habit getHabit() {
		return habit;
	}
	public void setHabit(Habit habit) {
		this.habit = habit;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public HabitProgress(Long id, Habit habit, LocalDate date, boolean completed) {
		super();
		this.id = id;
		this.habit = habit;
		this.date = date;
		this.completed = completed;
	}	
}
