package org.itechciv.dashboard.repository;

import org.itechciv.dashboard.model.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DietRepository extends JpaRepository<Diet, Long>{
	
	@Query(value = "select dt.*" + 
			" from dashboard.diet dt" + 
			" where dt.name =?1", nativeQuery = true)	
	Diet findDietByName(String name); 

}
