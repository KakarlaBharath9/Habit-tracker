package com.habit.entities;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name="habits")
public class Habit {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	private String name;
	private String description;
	@Enumerated(EnumType.STRING)
	private Frequency frequency = Frequency.DAILY;
	private LocalDate startDate;
	
	public enum Frequency{DAILY, WEEKLY}

	public Habit() {}
	//getters and setters
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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

	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public Habit(Long id, User user, String name, String description, Frequency frequency, LocalDate startDate) {
		super();
		this.id = id;
		this.user = user;
		this.name = name;
		this.description = description;
		this.frequency = frequency;
		this.startDate = startDate;
	}
	
	
}
