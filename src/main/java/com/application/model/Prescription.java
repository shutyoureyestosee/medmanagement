package com.application.model;

import jakarta.persistence.*;

@Entity
@Table(name = "prescriptions")
public class Prescription {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    private Appointment appointment;

//    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "doctor_id")
//    private Doctor doctor;
//
//    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "patient_id")
//    private Patient patient;
    

    private String comment;

	
  
	
	

	public Prescription(Appointment appointment, String comment) {
	super();
	this.appointment = appointment;
	this.comment = comment;
}


	public Appointment getAppointment() {
		return appointment;
	}


	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public Prescription() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	

    
}

