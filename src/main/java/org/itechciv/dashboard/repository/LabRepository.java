package org.itechciv.dashboard.repository;

import org.itechciv.dashboard.model.Lab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LabRepository extends JpaRepository<Lab, Long> {
	
	@Query(value = "select l.*" + 
			" from dashboard.lab l" + 
			" where l.prefix =?1", nativeQuery = true)	
	Lab findLabByPrefix(String prefix); 

}
