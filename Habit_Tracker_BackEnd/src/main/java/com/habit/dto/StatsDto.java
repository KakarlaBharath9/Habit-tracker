package com.habit.dto;

public class StatsDto {
	private int currentStreak;
	private int bestStreak;
	private double completionRate;
	
	public StatsDto() {}

	public int getCurrentStreak() {
		return currentStreak;
	}

	public void setCurrentStreak(int currentStreak) {
		this.currentStreak = currentStreak;
	}

	public int getBestStreak() {
		return bestStreak;
	}

	public void setBestStreak(int bestStreak) {
		this.bestStreak = bestStreak;
	}

	public double getCompletionRate() {
		return completionRate;
	}

	public void setCompletionRate(double completionRate) {
		this.completionRate = completionRate;
	}
	
	
}
