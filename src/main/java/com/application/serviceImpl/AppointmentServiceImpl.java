package com.application.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.exception.AppointmentAlreadyBookedException;
import com.application.exception.ResourceNotFoundException;
import com.application.model.Appointment;
import com.application.model.Doctor;
import com.application.model.Patient;
import com.application.repository.AppointmentRepository;
import com.application.repository.DoctorRepository;
import com.application.repository.PatientRepository;
import com.application.service.AppointmentService;


import jakarta.transaction.Transactional;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
	@Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;
    
    @Autowired
    private PatientRepository patientRepository;
    

    @Override
    public Appointment createAppointment(int p_id,int d_id, Appointment appointment) {
    	 Doctor doctor = doctorRepository.findById(d_id).orElse(null);
    	 Patient patient = patientRepository.findById(p_id).orElse(null);
    	 appointment.setDoctor(doctor);
    	 appointment.setPatient(patient);
    	 List<Appointment> appointments = appointmentRepository.findByDoctorIdAndDateAndTime(
    	            appointment.getDoctor().getId(), appointment.getDate(), appointment.getTime());
    	    if (!appointments.isEmpty()) {
    	        throw new AppointmentAlreadyBookedException("Appointment slot already taken for doctor with id " 
    	                + appointment.getDoctor() + " at " + appointment.getDate() + " " + appointment.getTime());
    	    }
    	    System.out.println(appointment.getDoctor().toString());
    	    // If appointment slot is not taken, book the appointment
    	    return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", id));
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> getAppointmentsByDoctorId(int doctorId) {
    	List<Appointment> findByDoctorId = appointmentRepository.findByDoctorId(doctorId);
    	Patient patient = findByDoctorId.get(0).getPatient();
    	System.out.println(patient);
        return appointmentRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<Appointment> getAppointmentsByPatientId(int patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

//    @Override
//    public void cancelAppointment(Long id) {
//        Appointment appointment = getAppointmentById(id);
//        appointment.setCancelled(true);
//        appointmentRepository.save(appointment);
//    }

    @Override
    public List<Appointment> getAppointmentsByDoctorIdAndDate(int doctorId, LocalDate date) {
        return appointmentRepository.findByDoctorIdAndDate(doctorId, date);
    }

    @Override
    public List<Appointment> getAppointmentsByDoctorIdAndDateRange(int doctorId, LocalDate startDate, LocalDate endDate) {
        return appointmentRepository.findByDoctorIdAndDateBetween(doctorId, startDate, endDate);
    }

	@Override
	public List<Appointment> getAppointmentByDate(LocalDate date) {
		// TODO Auto-generated method stub
		return appointmentRepository.findByDate(date);
	}

	@Override
	public void cancelAppointment(Long id) {
	 Appointment appointment = appointmentRepository.findById(id)
             .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", id));
	 
	 appointment.setStatus("cancel");
	 
	 appointmentRepository.save(appointment);
		
	}

	@Override
	public List<Appointment> getAppointmentsByPatientIdAndDate(int patientId, LocalDate date) {
		 return appointmentRepository.findByPatientIdAndDate(patientId, date);
	}
}
