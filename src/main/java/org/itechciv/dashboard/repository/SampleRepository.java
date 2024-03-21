package org.itechciv.dashboard.repository;

import org.itechciv.dashboard.model.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleRepository extends JpaRepository<Sample, Long> {
	
	@Query(value = "select s.*" + 
			" from dashboard.sample s" + 
			" where s.lab_no =?1", nativeQuery = true)	
	Sample findSampleByCode(String code); 


}
