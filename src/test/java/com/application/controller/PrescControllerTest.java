package com.application.controller;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.application.model.Appointment;
import com.application.model.Prescription;
import com.application.repository.AppointmentRepository;
import com.application.repository.PrescriptionRepository;

import com.application.serviceImpl.PrsecriptionServiceImpl;

public class PrescControllerTest {

    @Mock
    private AppointmentRepository appointmentRepo;
    
    @Mock
    private PrescriptionRepository prescRepo;
    
    @InjectMocks
    private PrsecriptionServiceImpl prescriptionService; 
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testCreatePrescription() {
        long aptId = 1L;
        Appointment appointment = new Appointment();
        appointment.setId(aptId);
        appointment.setStatus("scheduled");
        when(appointmentRepo.findById(aptId)).thenReturn(Optional.of(appointment));
        
        Prescription prescription = new Prescription();
        prescription.setComment("test comment");
        prescription.setAppointment(appointment);
        when(prescRepo.save(prescription)).thenReturn(prescription);
        
        Prescription createdPrescription = prescriptionService.createPrescription(aptId, prescription);
        
        verify(appointmentRepo, times(1)).findById(aptId);
        verify(prescRepo, times(1)).save(prescription);
        
        assertEquals("test comment", appointment.getDoctorComment());
        assertEquals("completed", appointment.getStatus());
        assertEquals(prescription, createdPrescription);
    }
}
