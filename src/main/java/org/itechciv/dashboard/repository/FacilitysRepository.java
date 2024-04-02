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
	Facilitys findFacilitysByCode(int code); 
	
	@Query(value = "select f.*" + 
			" from dashboard.facilitys f" + 
			" where f.old_code_facilitys_dhis2 =?1", nativeQuery = true)	
	Facilitys findFacilitysByOldCode(int code); 
	
	@Query(value = "select f.*" + 
			" from dashboard.facilitys f" + 
			" where f.old_facilitys_name =?1", nativeQuery = true)	
	Facilitys findFacilitysByOldName(String name); 
	
	@Query(value = "select f.*" + 
			" from dashboard.facilitys f" + 
			" where f.unique_facilitys_id =?1", nativeQuery = true)	
	Facilitys findFacilitysByUniqueCode(int code);
	
	@Query(value = "select r.*" + 
			" from dashboard.facilitys f" + 
			" where f.new_facilitys_long_name =?1", nativeQuery = true)	
	Facilitys findFacilitysByNewLongName(String name); 
	
	@Query(value = "select r.*" + 
			" from dashboard.facilitys f" + 
			" where f.new_facilitys_short_name =?1", nativeQuery = true)	
	Facilitys findFacilitysByShortName(String name); 
	
	@Query(value = "select r.*" + 
			" from dashboard.facilitys f" + 
			" where f.statutId =?1", nativeQuery = true)	
	Facilitys findFacilitysByStatutId(String name); 

}
