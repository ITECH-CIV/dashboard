package org.itechciv.dashboard.repository;

import org.itechciv.dashboard.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
	
	@Query(value = "select p.*" + 
			" from dashboard.partner p" + 
			" where p.name =?1 limit 1", nativeQuery = true)	
	Partner findPartnerByName(String name); 
	
}
