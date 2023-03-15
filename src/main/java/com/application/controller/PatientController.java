package com.application.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.exception.ResourceNotFoundException;
import com.application.model.Doctor;
import com.application.model.Patient;
import com.application.service.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public Patient addPatient(@RequestBody Patient patient) {
        return patientService.createPatient(patient);
    }
    
    @GetMapping("/byUser/{userId}")
    public Patient getDoctorByUserId(@PathVariable int userId) {
        return patientService.findPatientByUserId(userId);
    }

    @PutMapping("/{patientId}")
    public Patient updatePatient(@PathVariable int patientId, @RequestBody Patient patientDetails) throws ResourceNotFoundException {
        return patientService.updatePatient(patientId, patientDetails);
    }

    @DeleteMapping("/{patientId}")
    public void deletePatient(@PathVariable int patientId) throws ResourceNotFoundException {
        patientService.deletePatientById(patientId);
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{patientId}")
    public Patient getPatientById(@PathVariable int patientId) throws ResourceNotFoundException {
        return patientService.getPatientById(patientId);
    }
}


