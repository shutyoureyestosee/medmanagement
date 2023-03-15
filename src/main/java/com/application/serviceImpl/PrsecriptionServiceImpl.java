package com.application.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.model.Appointment;
import com.application.model.Doctor;
import com.application.model.Patient;
import com.application.model.Prescription;
import com.application.repository.AppointmentRepository;
import com.application.repository.DoctorRepository;
import com.application.repository.PatientRepository;
import com.application.repository.PrescriptionRepository;
import com.application.service.PrescriptionService;

@Service
public class PrsecriptionServiceImpl implements PrescriptionService {
	
	@Autowired
	private PrescriptionRepository prescRepo;
	
	
	  @Autowired
	  private AppointmentRepository appointmentRepo;
	    
	    @Autowired
	    private PatientRepository patientRepository;

	@Override
	public Prescription createPrescription(long apt_id,Prescription prescription) {
		 Appointment appointment = appointmentRepo.findById(apt_id).orElse(null);
		 
		 appointment.setDoctorComment(prescription.getComment());
		 appointment.setStatus("completed");
		 
		 appointmentRepo.save(appointment);
    	
    	 
    	 
    	  return prescRepo.save(prescription);
	}

}
