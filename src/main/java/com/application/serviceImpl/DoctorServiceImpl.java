package com.application.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.model.Appointment;
import com.application.model.Doctor;
import com.application.model.Schedule;
import com.application.repository.DoctorRepository;
import com.application.repository.ScheduleRepository;
import com.application.service.AppointmentService;
import com.application.service.DoctorService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    
    @Autowired
    private ScheduleRepository scheduleRepository;
    
    @Autowired
    private AppointmentService appointmentService;
    @Override
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }
    
    @Override
    public Doctor getDoctorById(int id) {
        return doctorRepository.findById(id).orElse(null);
    }
    
    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
    
    @Override
    public void deleteDoctorById(int id) {
        doctorRepository.deleteById(id);
    }
    
    @Override
    public List<Appointment> getAppointmentsByDoctorId(int doctorId) {
        List<Appointment> appointmentsByDoctorId = appointmentService.getAppointmentsByDoctorId(doctorId);
        return appointmentsByDoctorId;
        
    }

	@Override
	public Doctor updateDoctor(int id, Doctor doctor) {
		Optional<Doctor> target_doctor = doctorRepository.findById(id);
		target_doctor.get().setSpeciality(doctor.getSpeciality());
		target_doctor.get().setDegree(doctor.getDegree());
		doctorRepository.save(target_doctor.get());
		return target_doctor.get();
	}

	@Override
	public Doctor findDoctorByUserId(int userId) {
		 return doctorRepository.findByUserId(userId);
	}

	@Override
	public List<Doctor> findBySpeciality(String Speciality) {
		 return doctorRepository.findBySpeciality(Speciality);
	}

	
    
    
}

