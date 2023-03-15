package com.application.service;

import java.util.List;

import com.application.model.Appointment;
import com.application.model.Doctor;
import com.application.model.Schedule;

public interface DoctorService {
    Doctor createDoctor(Doctor doctor);
    Doctor getDoctorById(int id);
    List<Doctor> getAllDoctors();
    void deleteDoctorById(int id);
    List<Appointment> getAppointmentsByDoctorId(int doctorId);
	Doctor updateDoctor(int id, Doctor doctor);
	Doctor findDoctorByUserId(int userId);
	 List<Doctor> findBySpeciality(String Speciality);
   
}
