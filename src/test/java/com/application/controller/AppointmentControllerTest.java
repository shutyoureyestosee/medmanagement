package com.application.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.application.controller.AppointmentController;
import com.application.model.Appointment;
import com.application.model.Schedule;
import com.application.payload.AptReqst;
import com.application.service.AppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Mock
    private AppointmentService appointmentService;

    @InjectMocks
    private AppointmentController appointmentController;

    
    @Test
    void addAppointmentTest() throws Exception {
        AptReqst aptReqst = new AptReqst();
        aptReqst.setApt_date("2023-04-01");
        aptReqst.setApt_time("12:00");
        aptReqst.setApt_symptom("Fever");
        aptReqst.setApt_status("Scheduled");

        String jsonRequest = objectMapper.writeValueAsString(aptReqst);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/appointments/book/1/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Appointment appointment = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Appointment.class);

        assertNotNull(appointment.getId());
        assertEquals(LocalDate.parse("2023-04-01"), appointment.getDate());
        assertEquals(LocalTime.parse("12:00", DateTimeFormatter.ofPattern("HH:mm")), appointment.getTime());
        assertEquals("Fever", appointment.getSymptoms());
        assertEquals("Scheduled", appointment.getStatus());
    }

    @Test
    void deleteAppointmentTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/appointments/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        assertNull(mockMvc.perform(MockMvcRequestBuilders.get("/appointments/1"))
                .andReturn().getResponse().getContentAsString());
    }

    @Test
    void getAllAppointmentsTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/appointments"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<Appointment> appointments = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), List.class);

        assertNotNull(appointments);
        assertEquals(2, appointments.size());
    }

    @Test
    void getAppointmentByIdTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/appointments/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Appointment appointment = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Appointment.class);

        assertNotNull(appointment);
        assertEquals(1L, appointment.getId().longValue());
    }
    @Test
    void testGetAppointmentByDate() throws Exception {
        // setup
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment());
        appointments.add(new Appointment());
        when(appointmentService.getAppointmentByDate(LocalDate.of(2023, 3, 18))).thenReturn(appointments);

        // execute and assert
        mockMvc.perform(MockMvcRequestBuilders.get("/appointments/date?date=2023-03-18")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[{id:1,date:'2023-03-18',time:'08:00:00',schedule:{id:1},doctor:null}, {id:2,date:'2023-03-18',time:'09:00:00',schedule:{id:2},doctor:null}]"));
    }

    @Test
    void testGetAppointmentsByPatientId() throws Exception {
        // setup
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment());
        appointments.add(new Appointment());
        when(appointmentService.getAppointmentsByPatientId(1)).thenReturn(appointments);

        // execute and assert
        mockMvc.perform(MockMvcRequestBuilders.get("/appointments/patient/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[{id:1,date:'2023-03-18',time:'08:00:00',schedule:{id:1},doctor:null}, {id:2,date:'2023-03-18',time:'09:00:00',schedule:{id:2},doctor:null}]"));
    }
    
}