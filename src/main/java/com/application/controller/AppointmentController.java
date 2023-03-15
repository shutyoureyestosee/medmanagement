
package com.application.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.application.model.Appointment;
import com.application.model.Schedule;
import com.application.payload.AptReqst;
import com.application.service.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/book/{p_id}/{d_id}")
    public Appointment addAppointment(@PathVariable int p_id, @PathVariable int d_id, @RequestBody AptReqst appointment) {
    	
    	 System.out.println("Received appointment data: " + appointment.toString());
    	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    	    LocalDate date = LocalDate.parse(appointment.getApt_date());
    	    LocalTime time = LocalTime.parse(appointment.getApt_time(), formatter);

    	    System.out.println("Parsed date: " + date.toString());
    	    System.out.println("Parsed time: " + time.toString());
  
    
    	Appointment  newAppointment =new Appointment();
    	  newAppointment.setDate(date);
    	  newAppointment.setTime(time);
    	  newAppointment.setSymptoms(appointment.getApt_symptom());
    	  newAppointment.setStatus(appointment.getApt_status());
    	
        return appointmentService.createAppointment(p_id,d_id,newAppointment);
    }



    @DeleteMapping("/{appointmentId}")
    public void deleteAppointment(@PathVariable Long appointmentId) {
        appointmentService.cancelAppointment(appointmentId);
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{appointmentId}")
    public Appointment getAppointmentById(@PathVariable Long appointmentId) {
        return appointmentService.getAppointmentById(appointmentId);
    }
    @GetMapping("/date")
    public  List<Appointment> getAppointmentByDate(@RequestParam("date") 
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {
        return appointmentService.getAppointmentByDate(localDate);
    }

    @GetMapping("/patient/{patientId}")
    public List<Appointment> getAppointmentsByPatientId(@PathVariable int patientId) {
        return appointmentService.getAppointmentsByPatientId(patientId);
    }
    
    @GetMapping("/patient/{patientId}/{date}")
    public List<Appointment> getAppointmentsByPatientIdAndDate(@PathVariable int patientId,@PathVariable LocalDate date) {
        return appointmentService.getAppointmentsByPatientIdAndDate(patientId,date);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> getAppointmentsByDoctorId(@PathVariable int doctorId) {
        return appointmentService.getAppointmentsByDoctorId(doctorId);
    }

    @GetMapping("/doctor/{doctorId}/{date}")
    public List<Appointment> getAppointmentsByDoctorIdAndDate(@PathVariable int doctorId, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return appointmentService.getAppointmentsByDoctorIdAndDate(doctorId, date);
    }

    @GetMapping("/doctor/{doctorId}/{startDate}/{endDate}")
    public List<Appointment> getAppointmentsByDoctorIdAndDateRange(@PathVariable int doctorId, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return appointmentService.getAppointmentsByDoctorIdAndDateRange(doctorId, startDate, endDate);
    }
}

