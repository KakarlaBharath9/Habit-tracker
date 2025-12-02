package com.habit.dto;

public class SummaryDto {

    private int totalHabits;
    private int completedToday;
    private double avgCompletionRate;

    public int getTotalHabits() { return totalHabits; }
    public void setTotalHabits(int totalHabits) { this.totalHabits = totalHabits; }

    public int getCompletedToday() { return completedToday; }
    public void setCompletedToday(int completedToday) { this.completedToday = completedToday; }

    public double getAvgCompletionRate() { return avgCompletionRate; }
    public void setAvgCompletionRate(double avgCompletionRate) { this.avgCompletionRate = avgCompletionRate; }
}
