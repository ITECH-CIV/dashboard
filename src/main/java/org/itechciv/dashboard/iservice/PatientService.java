package org.itechciv.dashboard.iservice;

import org.itechciv.dashboard.model.Patient;

public interface PatientService extends GenericService<Patient, Long>{
	
	Patient findPatientByCode(String code); 

}
