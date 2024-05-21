package org.itechciv.dashboard.repository;

import org.itechciv.dashboard.model.AgeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AgeCategoryRepository extends JpaRepository<AgeCategory, Long> {

    @Query(value = "select ac.*" + 
			" from dashboard.age_category ac" + 
			" where ac.label =?1", nativeQuery = true)	
	AgeCategory findCategorieAgeByLabel(String prefix); 

}
