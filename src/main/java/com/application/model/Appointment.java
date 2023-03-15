package com.application.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;

@Entity
@Table(name = "appointments")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Appointment {
    
 

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
//    @JoinColumn(name = "doctor_id")
//	@JsonBackReference(value="doctor-movement")
    private Doctor doctor;

    @ManyToOne
//    @JoinColumn(name = "patient_id")
//    @JsonBackReference(value="patient-movement")
    private Patient patient;

    @Column(name = "date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    
    @Column(name = "time")
    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime time;
    @Column(name = "status")
    private String status;
    
    private String symptoms;
    
    private String DoctorComment;

	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Appointment(Doctor doctor, Patient patient, LocalDate date, LocalTime time, String status, String symptoms,
			String doctorComment) {
		super();
		this.doctor = doctor;
		this.patient = patient;
		this.date = date;
		this.time = time;
		this.status = status;
		this.symptoms = symptoms;
		DoctorComment = doctorComment;
	}

 

	public String getDoctorComment() {
		return DoctorComment;
	}



	public void setDoctorComment(String doctorComment) {
		DoctorComment = doctorComment;
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

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

//	@Override
//	public String toString() {
//		return "Appointment [id=" + id + ", doctor=" + doctor + ", patient=" + patient + ", date=" + date + ", time="
//				+ time + ", status=" + status + ", symptoms=" + symptoms + "]";
//	}


	
	

    
   
}
