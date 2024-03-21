package org.itechciv.dashboard.repository;

import org.itechciv.dashboard.model.Facilitys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilitysRepository extends JpaRepository<Facilitys, Long> {
	
	@Query(value = "select f.*" + 
			" from dashboard.facilitys f" + 
			" where f.code_site =?1", nativeQuery = true)	
	Facilitys findFacilitysByCode(String name); 


}
