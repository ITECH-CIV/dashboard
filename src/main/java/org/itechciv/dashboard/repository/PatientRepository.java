package org.itechciv.dashboard.repository;

import org.itechciv.dashboard.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
	
	@Query(value = "select p.*" + 
			" from dashboard.patient p" + 
			" where p.subjectid =?1", nativeQuery = true)	
	Patient findPatientByCode(String code); 

}
