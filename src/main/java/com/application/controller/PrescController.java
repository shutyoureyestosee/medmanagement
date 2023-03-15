package com.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.model.Prescription;
import com.application.service.PrescriptionService;

@RestController
@RequestMapping("/prescribe")
public class PrescController {
	
	 @Autowired
	 private PrescriptionService prescriptionService;
	
	
	@PostMapping("presc/{apt_id}")
    public Prescription createPresc(@PathVariable long apt_id, @RequestBody  Prescription prescription) {
        return prescriptionService.createPrescription(apt_id,prescription);
    }



}
