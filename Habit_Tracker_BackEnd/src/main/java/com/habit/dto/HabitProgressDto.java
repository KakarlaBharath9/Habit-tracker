package com.habit.dto;

public class HabitProgressDto {
	private Long id;
	private Long habitId;
	private String date; //yyyy-mm-dd
	private boolean completed;
	
	public HabitProgressDto() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHabitId() {
		return habitId;
	}

	public void setHabitId(Long habitId) {
		this.habitId = habitId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	
}
