package com.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.application.model.Doctor;
import com.application.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

	  @Query("SELECT p FROM Patient p WHERE p.user.id = ?1")
	    Patient findByUserId(int userId);

   // Optional<Patient> findByEmail(String email);

   // Optional<Patient> findByPhoneNumber(String phoneNumber);
    
    // add other methods as per your requirements
}