package com.application.service;

import org.springframework.stereotype.Service;

import com.application.model.Prescription;

public interface PrescriptionService {

	Prescription createPrescription(long apt_id, Prescription prescription);
	

}
