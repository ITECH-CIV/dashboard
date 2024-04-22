package org.itechciv.dashboard.repository;

import org.itechciv.dashboard.model.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, Long> {
	
	@Query(value = "select a.*" + 
			" from dashboard.analysis a" + 
			" where a.lab_no =?1", nativeQuery = true)	
	Analysis findAnalysisByCode(String code); 


}
