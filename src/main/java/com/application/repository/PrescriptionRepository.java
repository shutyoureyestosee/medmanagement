package com.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.model.Doctor;
import com.application.model.Patient;
import com.application.model.Prescription;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

//    List<Prescription> findByDoctor(Doctor doctor);
//
//    List<Prescription> findByPatient(Patient patient);
//
//	List<Prescription> findByPatientId(int patientId);
    
    // add other methods as per your requirements
}