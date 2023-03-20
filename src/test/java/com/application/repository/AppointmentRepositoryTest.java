package com.application.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.application.model.Appointment;
import com.application.model.Doctor;
import com.application.model.Patient;

@DataJpaTest
public class AppointmentRepositoryTest {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    public void testFindByPatientId() {
        List<Appointment> appointments = appointmentRepository.findByPatientId(1);
        assertNotNull(appointments);
        assertEquals(1, appointments.size());
    }

    @Test
    public void testFindByDoctorId() {
        List<Appointment> appointments = appointmentRepository.findByDoctorId(1);
        assertNotNull(appointments);
        assertEquals(1, appointments.size());
    }

    @Test
    public void testFindByDoctorIdAndDate() {
        List<Appointment> appointments = appointmentRepository.findByDoctorIdAndDate(1, LocalDate.of(2022, 4, 5));
        assertNotNull(appointments);
        assertEquals(1, appointments.size());
    }

    @Test
    public void testFindByDoctorIdAndDateBetween() {
        List<Appointment> appointments = appointmentRepository.findByDoctorIdAndDateBetween(1, LocalDate.of(2022, 4, 1), LocalDate.of(2022, 4, 7));
        assertNotNull(appointments);
        assertEquals(1, appointments.size());
    }

    @Test
    public void testFindByDoctorIdAndDateAndTime() {
        List<Appointment> appointments = appointmentRepository.findByDoctorIdAndDateAndTime(1, LocalDate.of(2022, 4, 5), LocalTime.of(10, 0));
        assertNotNull(appointments);
        assertEquals(1, appointments.size());
    }

    @Test
    public void testFindByDate() {
        List<Appointment> appointments = appointmentRepository.findByDate(LocalDate.of(2022, 4, 5));
        assertNotNull(appointments);
        assertEquals(1, appointments.size());
    }

    @Test
    public void testFindByPatientIdAndDate() {
        List<Appointment> appointments = appointmentRepository.findByPatientIdAndDate(1, LocalDate.of(2022, 4, 5));
        assertNotNull(appointments);
        assertEquals(1, appointments.size());
    }
}