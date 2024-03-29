package org.itechciv.dashboard.repository;

import org.itechciv.dashboard.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
	
	@Query(value = "select r.*" + 
			" from dashboard.region r" + 
			" where r.code_region =?1", nativeQuery = true)	
	Region findRegionByCode(int code); 
	
	@Query(value = "select r.*" + 
			" from dashboard.region r" + 
			" where r.name =?1", nativeQuery = true)	
	Region findRegionByName(String name); 
	
	
}
