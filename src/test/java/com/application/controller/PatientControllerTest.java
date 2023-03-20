package com.application.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.application.model.Patient;
import com.application.service.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {

    private MockMvc mockMvc;
    
    @InjectMocks
    private PatientController patientController;
    
    @Mock
    private PatientService patientService;
    
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
    }
    
    @Test
    public void testAddPatient() throws Exception {
        Patient patient = new Patient();
        patient.setId(1);
        
        when(patientService.createPatient(any(Patient.class))).thenReturn(patient);
        
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/patients")
                .content(asJsonString(patient))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        
        assert(status == 200);
        assert(content != null);
    }
    
    @Test
    public void testGetDoctorByUserId() throws Exception {
        Patient patient = new Patient();
        patient.setId(1);
        patient.getUser().setId(1);
        
        when(patientService.findPatientByUserId(any(Integer.class))).thenReturn(patient);
        
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/patients/byUser/1")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        
        assert(status == 200);
        assert(content != null);
    }
    
    @Test
    public void testUpdatePatient() throws Exception {
        Patient patient = new Patient();
        patient.setId(1);
        patient.getUser().setId(1);
        
        when(patientService.updatePatient(any(Integer.class), any(Patient.class))).thenReturn(patient);
        
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/patients/1")
                .content(asJsonString(patient))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        
        assert(status == 200);
        assert(content != null);
    }
    
    @Test
    public void testDeletePatient() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/patients/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        
        // verify that the delete method of the service is called
        verify(patientService, times(1)).deletePatientById(1);
    }
    
    public void testGetAllPatients() throws Exception {
        List<Patient> patients = new ArrayList<Patient>();
        Patient patient1 = new Patient();
        patient1.setId(1);
        User user1 = new User();
        user1.setId(1);
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("johndoe@example.com");
        patient1.setUser(user1);
        patients.add(patient1);
        
        Patient patient2 = new Patient();
        patient2.setId(2);
        User user2 = new User();
        user2.setId(2);
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setEmail("janedoe@example.com");
        patient2.setUser(user2);
        patients.add(patient2);
        
        Mockito.when(patientService.getAllPatients()).thenReturn(patients);
        
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/patients").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        
        String expectedResponse = "[{\"id\":1,\"user\":{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"johndoe@example.com\"}},{\"id\":2,\"user\":{\"id\":2,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"janedoe@example.com\"}}]";
        JSONAssert.assertEquals(expectedResponse, mvcResult.getResponse().getContentAsString(), false);
    }
}
