package com.application.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.model.Appointment;
import com.application.model.Patient;
import com.application.model.Prescription;
import com.application.repository.PatientRepository;
import com.application.repository.PrescriptionRepository;
import com.application.service.AppointmentService;
import com.application.service.PatientService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    
    @Autowired
    private AppointmentService appointmentService;
    
    @Override
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }
    
    @Override
    public Patient getPatientById(int id) {
        return patientRepository.findById(id).orElse(null);
    }
    
    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
    
    @Override
    public void deletePatientById(int id) {
        patientRepository.deleteById(id);
    }
    
    @Override
    public List<Appointment> getAppointmentsByPatientId(int patientId) {
        List<Appointment> appointmentsByPatientId = appointmentService.getAppointmentsByPatientId(patientId);
        return appointmentsByPatientId;
    }
    
    @Override
    public Prescription createPrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }
    
//    @Override
//    public List<Prescription> getPrescriptionsByPatientId(int patientId) {
//        return prescriptionRepository.findByPatientId(patientId);
//    }

	@Override
	public Patient updatePatient(int patientId, Patient patientDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patient findPatientByUserId(int userId) {
		return patientRepository.findByUserId(userId);
	}

	@Override
	public List<Prescription> getPrescriptionsByPatientId(int patientId) {
		// TODO Auto-generated method stub
		return null;
	}
}
