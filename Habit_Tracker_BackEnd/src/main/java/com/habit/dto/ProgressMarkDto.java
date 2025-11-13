package com.habit.dto;

public class ProgressMarkDto {
	private Long habitId;
	private String date;
	private boolean completed;
	
	public ProgressMarkDto() {}

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
