package org.itechciv.dashboard.repository;

import org.itechciv.dashboard.model.Regimen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegimenRepository extends JpaRepository<Regimen, Long>{
	
	@Query(value = "select DISTINCT name, id" + 
			" from dashboard.regimen rg" + 
			" where rg.name =?1", nativeQuery = true)	
	Regimen findRegimenByName(String name); 

}
