package org.itechciv.dashboard.impservice;

import org.itechciv.dashboard.iservice.PatientService;
import org.itechciv.dashboard.model.Patient;
import org.itechciv.dashboard.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatientServiceImpl extends GenericServiceImpl<Patient, Long> implements PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public Patient findPatientByCode(String code) {
		
	Patient p = new Patient();
		
       try 
		
		{ 
			p = patientRepository.findPatientByCode(code);
			
			if( p!=null)
			{ 
				return p;
			} 
			else 
			{ 
				return null;
			}
		}
		catch(Exception ex) { 
			ex.printStackTrace();
			return null;
		}	
	}
	
	

}
