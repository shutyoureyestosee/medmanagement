package com.application.model;


import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "schedules")
public class Schedule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
//    @JoinColumn(name = "doctor_id")
//    @JsonBackReference(value="doctor-sch")
    private Doctor doctor;

    @Column(name = "date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    
    @Column(name = "start_time")
    @JsonFormat(pattern="HH:mm")
    private LocalTime startTime;
    
    @Column(name = "end_time")
    @JsonFormat(pattern="HH:mm")
    private LocalTime endTime;


    

	public Schedule(Doctor doctor, LocalDate date, LocalTime startTime, LocalTime endTime) {
		super();
		this.doctor = doctor;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
	}



	public Schedule() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public LocalDate getDate() {
		return date;
	}



	public void setDate(LocalDate date) {
		this.date = date;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	

   
}

