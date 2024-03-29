package org.itechciv.dashboard.repository;

import org.itechciv.dashboard.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
	
	@Query(value = "select d.*" + 
			" from dashboard.district d" + 
			" where d.code_district =?1", nativeQuery = true)	
	District findDistrictByCode(int code); 
	
	@Query(value = "select d.*" + 
			" from dashboard.district d" + 
			" where d.sequence_district =?1", nativeQuery = true)	
	District findDistrictBySequenceDistrict(int code); 
	
	@Query(value = "select d.*" + 
			" from dashboard.district d" + 
			" where d.name =?1", nativeQuery = true)	
	District findDistrictByName(String name); 

}
