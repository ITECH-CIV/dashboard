package org.itechciv.dashboard.repository;

import org.itechciv.dashboard.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {

	@Query(value = " SELECT SUM(total_views) as total_views FROM dashboard.page ", nativeQuery = true)	
	long getTotalViews(); 

    	
	@Query(value = "select * " + 
			" from dashboard.page pg" + 
			" where pg.label =?1", nativeQuery = true)	
	Page findPageByLabel(String label); 


}
