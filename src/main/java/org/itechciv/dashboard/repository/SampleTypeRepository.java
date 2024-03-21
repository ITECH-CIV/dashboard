package org.itechciv.dashboard.repository;

import org.itechciv.dashboard.model.SampleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleTypeRepository extends JpaRepository<SampleType, Long> {
	
	@Query(value = "select st.*" + 
			" from dashboard.sample_type st" + 
			" where st.label =?1", nativeQuery = true)	
	SampleType findSampleTypeByName(String name); 


}
