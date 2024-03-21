package org.itechciv.dashboard.repository;

import org.itechciv.dashboard.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
	
	@Query(value = "select t.*" + 
			" from dashboard.test t" + 
			" where t.study =?1", nativeQuery = true)	
	Test findTestByName(String name); 

}
