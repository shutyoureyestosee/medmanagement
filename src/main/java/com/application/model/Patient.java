package com.application.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;

@Entity
@Table(name = "patients")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Patient {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private User user = new User();

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Patient(User user) {
		super();
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", user=" + user + "]";
	}
	
//	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "patient")
//	@JsonManagedReference(value="patient-movement")
//	List<Appointment> appointments = new ArrayList<>();
//	
	
	
	

	
	
	
    
    
}

