package com.habit.dto;

public class HabitDto {
	private Long id;
	private String name;
	private String description;
	private String frequency;
	private String startDate;
	private StatsDto stats;
	
	public HabitDto() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public StatsDto getStats() {
		return stats;
	}

	public void setStats(StatsDto stats) {
		this.stats = stats;
	}
	
	
}
