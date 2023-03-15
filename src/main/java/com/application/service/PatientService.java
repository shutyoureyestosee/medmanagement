package com.application.service;

import java.util.List;

import com.application.model.Appointment;
import com.application.model.Patient;
import com.application.model.Prescription;

public interface PatientService {
    Patient createPatient(Patient patient);
    Patient getPatientById(int id);
    List<Patient> getAllPatients();
    void deletePatientById(int id);
    List<Appointment> getAppointmentsByPatientId(int patientId);
    Prescription createPrescription(Prescription prescription);
    List<Prescription> getPrescriptionsByPatientId(int patientId);
	Patient updatePatient(int patientId, Patient patientDetails);
	Patient findPatientByUserId(int userId);
}

