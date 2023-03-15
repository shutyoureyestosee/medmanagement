package com.application.service;

import java.time.LocalDate;
import java.util.List;

import com.application.model.Appointment;

public interface AppointmentService {
    Appointment createAppointment(int p_id,int d_id, Appointment a);
    Appointment getAppointmentById(Long id);
    List<Appointment> getAllAppointments();
    List<Appointment> getAppointmentsByDoctorId(int doctorId);
    List<Appointment> getAppointmentsByPatientId(int patientId);
    void cancelAppointment(Long id);
	List<Appointment> getAppointmentsByDoctorIdAndDate(int doctorId, LocalDate date);
	List<Appointment> getAppointmentsByDoctorIdAndDateRange(int doctorId, LocalDate startDate, LocalDate endDate);
	List<Appointment> getAppointmentByDate(LocalDate date);
	List<Appointment> getAppointmentsByPatientIdAndDate(int patientId, LocalDate date);
	
}
